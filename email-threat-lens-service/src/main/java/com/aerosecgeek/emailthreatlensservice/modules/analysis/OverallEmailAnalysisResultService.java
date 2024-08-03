package com.aerosecgeek.emailthreatlensservice.modules.analysis;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisResult;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisResultType;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisStatus;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.OverallEmailAnalysisResult;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class OverallEmailAnalysisResultService {
    private final OverallEmailAnalysisResultRepository overallEmailAnalysisResultRepository;

    private final Object lock = new Object();

    @Transactional
    public OverallEmailAnalysisResult createNewResult(Email email){
        OverallEmailAnalysisResult result = new OverallEmailAnalysisResult(email);
        return saveResult(result);
    }

    public OverallEmailAnalysisResult saveResult(OverallEmailAnalysisResult result){
        return overallEmailAnalysisResultRepository.save(result);
    }

    public OverallEmailAnalysisResult getResultByUuid(UUID uuid){
        return overallEmailAnalysisResultRepository.findById(uuid).orElse(null);
    }

    @Transactional
    public OverallEmailAnalysisResult updateAnalysisResult(UUID overallResultUuid, AnalysisResult analysisResult, AnalysisResultType type){
        synchronized (lock){
            var result = getOverallEmailAnalysisResult(overallResultUuid);
            if (result == null) return null;
            if(type.equals(AnalysisResultType.HEADER)){
                result.setHeaderAnalysisResult(analysisResult);
            }
            else if(type.equals(AnalysisResultType.BODY)){
                result.setBodyAnalysisResult(analysisResult);
            }
            else if(type.equals(AnalysisResultType.ATTACHMENT)){
                result.setAttachmentAnalysisResult(analysisResult);
            }
            else{
                log.error("Invalid analysis result type: {}", type);
                return null;
            }
            return saveResult(result);
        }
    }
    
    @Transactional
    public void updateAnalysisStatus(UUID overallResultUuid,AnalysisResultType type, AnalysisStatus status){
        synchronized (lock){
            var result = getOverallEmailAnalysisResult(overallResultUuid);
            if (result == null) return;
            if(type.equals(AnalysisResultType.HEADER)){
                result.getHeaderAnalysisResult().setStatus(status);
            }
            else if(type.equals(AnalysisResultType.BODY)){
                result.getBodyAnalysisResult().setStatus(status);
            }
            else if(type.equals(AnalysisResultType.ATTACHMENT)){
                result.getAttachmentAnalysisResult().setStatus(status);
            }
            saveResult(result);
        }

    }

    private OverallEmailAnalysisResult getOverallEmailAnalysisResult(UUID overallResultUuid) {
        var result = getResultByUuid(overallResultUuid);
        if(result == null){
            log.error("Overall result not found for uuid: {}", overallResultUuid);
            return null;
        }
        return result;
    }
}
