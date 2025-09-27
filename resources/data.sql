CREATE TABLE account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    cardId VARCHAR(10),
    pin VARCHAR(10),
    currentBalance DOUBLE,
    isLocked BOOLEAN
);

INSERT INTO account (username, cardId, pin, currentBalance, isLocked) VALUES
('AN THAI BINH', '090103', '0607', 950000.0, false),
('TRAN TRONG AN', '090104', '1212', 1525000.0, false),
('HUYNH TAN KIET', '090303', '7890', 2442000.0, false),
('LE TRONG PHUC', '092201', '2220', 5500500.0, false),
('0', '0', '0', 100000.0, false);