ECHO Data File,Start Range,Gain > log.csv

CALL run_4-7_file.bat data\HOD-2012.csv
CALL run_4-7_file.bat data\HOD-2013.csv

CALL run_4-7_file.bat data\HOU-2012.csv
CALL run_4-7_file.bat data\HOU-2013.csv

move log.csv log_4-7.csv