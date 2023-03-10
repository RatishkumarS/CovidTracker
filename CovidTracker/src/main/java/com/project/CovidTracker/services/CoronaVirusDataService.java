
package com.project.CovidTracker.services;

import com.project.CovidTracker.model.Coronavirusdata;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service

public class CoronaVirusDataService {
    private List<Coronavirusdata> allList=new ArrayList<>();

    public List<Coronavirusdata> getAllList() {
        return allList;
    }

    private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    @PostConstruct
    @Scheduled(cron = "* * * * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
            List<Coronavirusdata> newList=new ArrayList<>();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
            HttpResponse<String> httpresponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(httpresponse.body());
            StringReader reader= new StringReader(httpresponse.body());
            Iterable<CSVRecord> records= CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for(CSVRecord record: records)
            {
//                String State= record.get("Province/State");
//                System.out.println(State);
                Coronavirusdata c=new Coronavirusdata();
                c.setCountry(record.get("Country/Region"));
                c.setState(record.get("Province/State"));
                c.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
                int latestCases=Integer.parseInt(record.get(record.size()-1));
                int prevdayCase=Integer.parseInt(record.get(record.size()-2));
                c.setPrevdaycasesDiff(latestCases-prevdayCase);
                newList.add(c);
                //System.out.println(newList);
            }
            this.allList=newList;
    }

}
