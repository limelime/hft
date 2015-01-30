SET FILENAME=%1
SET Low=%2
SET PERC=%3
java -jar hft.jar %FILENAME% %LOW% %PERC% > %FILENAME%_%LOW%_%PERC%.csv
move /y %FILENAME%_%LOW%_%PERC%.csv output

