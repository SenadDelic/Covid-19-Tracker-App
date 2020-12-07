package tracker.covid.service;

import com.opencsv.CSVReader;
import lombok.Getter;
import org.springframework.stereotype.Service;
import tracker.covid.model.Covid;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class CovidDataService {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // MM/DD/YYYY HH:mm:ss
    private static final String VIRUS_DATA_URL =
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/12-03-2020.csv";
    private List<Covid> covidData = new ArrayList<>();

    @PostConstruct
    private void getVirusData() throws IOException {
        URL url = new URL(VIRUS_DATA_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        CSVReader csvReader = new CSVReader(input);

        readCSVFile(csvReader);
    }

    private void readCSVFile(CSVReader csvReader) throws IOException {
        List<Covid> newStats = new ArrayList<>();
        String[] line;
        int i = 0;

        while ((line = csvReader.readNext()) != null) {
            if (i == 0) {
                i++;
                continue;
            }

            /*
             * The NumberFormatException is basically caused because the input string is not well formatted or
             * illegal while parsing into a numerical value. So, to avoid this exception,
             * the input string provided has to be well formatted.
             */
            Covid covid = new Covid();
            try {
                covid.setLastUpdate(LocalDateTime.parse(line[4], FORMATTER));
                covid.setConfirmed(Long.valueOf(line[7]));
                covid.setDeath(Long.valueOf(line[8]));
                covid.setRecovered(Long.valueOf(line[9]));
                covid.setActive(Long.valueOf(line[10]));
                covid.setCombinedKey(line[11]);

            } catch (NumberFormatException ex) { //request for well-formatted string
                System.err.println("Invalid string in argument");
            }
            newStats.add(covid);
        }
        this.covidData = newStats;
    }
}
