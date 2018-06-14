# Data Extraction Service
   
  **Introduction**  
       As the name indicates, this is a data extraction service. Given a single record or a file, it extracts necessary data from it.
      
  **Project Detail**  
       This project is developed as a micro service based on Java8, Maven, Springboot and Swagger.
  
  **Usage Instructions**  
  
  If you are running this project from and IDE, then it would be enough to run the main class _DataExtractionApplication.java_  
  
  If you want to run it from console or command line.   
           1.  compile the code from project's root folder using ``mvn clean package``  
           2.  Then ``java -jar target/data-extraction-0.0.1-SNAPSHOT.jar``
           
  Project is setup to run on port 8090 by default. If you wish to change it, then please start with this command  
            ``java  -Dserver.port=<yourport> -jar target/data-extraction-0.0.1-SNAPSHOT.jar``
            
  Since there is no Ui to visualize the results, I have added [Swagger](https://swagger.io/) support to the project.  
  Swagger Ui is reachable on [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html) once you have started the application.  
  (Change the port if you are starting with different port.)  
  
  On Swagger Ui, on clicking Data Extractor, you will see two endpoint exposed   
    1. _/data_  This endpoint is to send single record. For example put this in the parameter _VF1KMS40A36042123,KB,H1,RENAULT_ and click **Try it out!**  
    2. _/data/file_ this endpoint is to upload a csv file.  Click _Choose File_ and upload the file and click **Try it out!**
  
  
  In both the cases you should be able to see the result below in **JSON** format.  
  
  Alternatively, you can use any REST client like Postman to trigger the endpoints.