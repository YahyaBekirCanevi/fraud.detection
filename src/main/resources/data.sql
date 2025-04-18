CREATE TABLE blacklisted_user (
    user_id VARCHAR(255) PRIMARY KEY,
    description VARCHAR(512),
    created_at TIMESTAMP
);

INSERT INTO blacklisted_user (user_id, description, created_at) VALUES
('fraudster001', 'Involved in suspicious large transactions', CURRENT_TIMESTAMP),
('scammer123', 'Chargeback fraud flagged by PSP', CURRENT_TIMESTAMP),
('moneylaunder007', 'Listed by compliance team - possible laundering', CURRENT_TIMESTAMP);
