SELECT * FROM stockbuild; /*Gibt alle Elemente aus der Datenbank aus */

/*Primarykey*/
SELECT * FROM stockbuild WHERE "StockBuildID" = 1; /*Gibt alle Elemente mit dem Primarykey aus*/

/*Date*/
SELECT * FROM stockbuild WHERE "Date" = '2019-06-27'; /*Gibt alle Elemente aus, die exakt das selbe Datum haben*/
SELECT * FROM stockbuild WHERE "Date" < '2019-06-27'; /*Gibt alle Elemente aus, die vor dem Angegebenen Datum sind*/
SELECT * FROM stockbuild WHERE "Date" > '2019-06-27'; /*Gibt alle Elemente aus, die nach dem Angegebenen Datum sind*/
SELECT * FROM stockbuild WHERE "Date" > '2018-06-02' AND "Date" < '2020-06-02'; /*Gibt alle Elemente zwischen dem ersten und dem zweiten Datum aus*/

/*Symbol*/
SELECT * FROM stockbuild1 WHERE "Symbol" = 'FSE/EON_X'; /*Gibt alle Elemente aus, die das Symbol haben*/

/*Value*/
SELECT * FROM stockbuild WHERE "Open" IS NOT NULL ORDER BY "Open" DESC; /*Gibt alle Elemente von Open absteigend sortiert zurück (Höchster zu erst)*/
SELECT MAX ("Open") FROM stockbuild;
SELECT * FROM stockbuild WHERE "Open" IS NOT NULL ORDER BY "Open" ASC; /*Gibt alle Elemente von Open aufsteigend sortiert zurück (Niedrigster zu erst)*/
SELECT MIN ("Open") FROM stockbuild;