create schema zerobase;

CREATE TABLE wifiInfo (
    managerNumber VARCHAR(255) PRIMARY KEY,
    borough VARCHAR(255),
    wifiName VARCHAR(255),
    roadAddress VARCHAR(255),
    detailAddress VARCHAR(255),
    installationFloor VARCHAR(255),
    installationType VARCHAR(255),
    installationAgency VARCHAR(255),
    serviceType VARCHAR(255),
    networkType VARCHAR(255),
    installationYear INT,
    indoorOutdoorType VARCHAR(255),
    wifiConnectionEnvironment VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE,
    workDate TIMESTAMP
);



select * from wifiinfo;
SELECT COUNT(*) FROM wifiinfo;
-- drop table wifiinfo;

SELECT * FROM wifiInfo WHERE borough = '영등포구';

CREATE TABLE posHistory (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    latitude DOUBLE,
    longitude DOUBLE,
    visitTime TIMESTAMP
);
select * from posHistory;



