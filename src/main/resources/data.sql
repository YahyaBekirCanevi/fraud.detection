CREATE TABLE blacklisted_user (
    user_id VARCHAR(255) PRIMARY KEY
);

INSERT INTO blacklisted_user (user_id) VALUES ('user123');
INSERT INTO blacklisted_user (user_id) VALUES ('fraudster001');
