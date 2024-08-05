-- V1__Initial_Setup.sql
CREATE TABLE IF NOT EXISTS email (
                                     uuid UUID PRIMARY KEY,
                                     received_date TIMESTAMP,
                                     body TEXT,
                                     from_address VARCHAR(255),
                                     subject VARCHAR(255),
                                     to_address VARCHAR(255),
                                     headers JSONB
);

CREATE TABLE IF NOT EXISTS overall_email_analysis_result (
                                                             uuid UUID PRIMARY KEY,
                                                             email_uuid UUID,
                                                             attachment_description TEXT,
                                                             attachment_outcome VARCHAR(50),
                                                             attachment_status VARCHAR(50),
                                                             body_description TEXT,
                                                             body_outcome VARCHAR(50),
                                                             body_status VARCHAR(50),
                                                             header_description TEXT,
                                                             header_outcome VARCHAR(50),
                                                             header_status VARCHAR(50),
                                                             FOREIGN KEY (email_uuid) REFERENCES email (uuid)
);

CREATE TABLE IF NOT EXISTS virus_total_analysis_result (
                                                           uuid UUID PRIMARY KEY,
                                                           analysis_id VARCHAR(255),
                                                           url TEXT,
                                                           completed BOOLEAN,
                                                           last_scan_date TIMESTAMP,
                                                           outcome VARCHAR(50)
);