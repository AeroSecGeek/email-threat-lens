package com.aerosecgeek.emailthreatlensservice.modules.testdata;

public class VirusTotalResponse {
    private VirusTotalResponse(){}

    public static String triggerUrlScanResponse(){
        return """
                {
                    "data": {
                        "type": "analysis",
                        "id": "u-dd014af5ed6b38d9130e3f466f850e46d21b951199d53a18ef29ee9341614eaf-1722769335",
                        "links": {
                            "self": "https://www.virustotal.com/api/v3/analyses/u-dd014af5ed6b38d9130e3f466f850e46d21b951199d53a18ef29ee9341614eaf-1722769335"
                        }
                    }
                }""";
    }

    public static String getUrlAnalysisReportResponse(){
        return """
                {
                    "data": {
                        "attributes": {
                            "date": 1591701032,
                            "results": {
                                "ADMINUSLabs": {
                                    "category": "harmless",
                                    "engine_name": "ADMINUSLabs",
                                    "method": "blacklist",
                                    "result": "clean"
                                },
                                "AegisLab WebGuard": {
                                    "category": "harmless",
                                    "engine_name": "AegisLab WebGuard",
                                    "method": "blacklist",
                                    "result": "clean"
                                },
                                "AlienVault": {
                                    "category": "harmless",
                                    "engine_name": "AlienVault",
                                    "method": "blacklist",
                                    "result": "clean"
                                },
                                "Antiy-AVL": {
                                    "category": "harmless",
                                    "engine_name": "Antiy-AVL",
                                    "method": "blacklist",
                                    "result": "clean"
                                },
                                "Artists Against 419": {
                                    "category": "harmless",
                                    "engine_name": "Artists Against 419",
                                    "method": "blacklist",
                                    "result": "clean"
                                },
                                "AutoShun": {
                                    "category": "undetected",
                                    "engine_name": "AutoShun",
                                    "method": "blacklist",
                                    "result": "unrated"
                                },
                                "Avira": {
                                    "category": "harmless",
                                    "engine_name": "Avira",
                                    "method": "blacklist",
                                    "result": "clean"
                                },
                                "BADWARE.INFO": {
                                    "category": "harmless",
                                    "engine_name": "BADWARE.INFO",
                                    "method": "blacklist",
                                    "result": "clean"
                                }
                            },
                            "stats": {
                                "harmless": 7,
                                "malicious": 0,
                                "suspicious": 0,
                                "timeout": 0,
                                "undetected": 1
                            },
                            "status": "completed"
                        },
                        "id": "u-9d11db1b0q1200ba75016e4c010bc93836366881d021a658ua7f85a8b65c3c1e-1591701032",
                        "type": "analysis"
                    }
                }""";
    }

    public static String getPendingUrlAnalysisReport(){
        return """
                {
                    "data": {
                        "attributes": {
                            "date": 1591701032,
                            "status": "in_progress"
                        },
                        "id": "u-9d11db1b0q1200ba75016e4c010bc93836366881d021a658ua7f85a8b65c3c1e-1591701032",
                        "type": "analysis"
                    }
                }""";
    }
}
