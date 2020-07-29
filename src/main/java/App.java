

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String filename = "data.json";
        List<Employee> employees = jsonToEmployees(filename);
        printAll(employees);
    }

    private static void printAll(List<Employee> employees) {
        for (Employee employee : employees)
            System.out.println(employee.toString());
    }

    private static List<Employee> jsonToEmployees(String filename) {
        List<Employee> employees = new ArrayList<>();
        JSONParser parser = new JSONParser();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
            try {
            Object obj = parser.parse(new FileReader(filename));
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObj = (JSONObject)jsonArray.get(i);
                Employee employee = gson.fromJson(jsonObj.toString(), Employee.class);
                employees.add(employee);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
