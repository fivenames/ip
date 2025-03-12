package Nimbus.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task{
    private final String by;

    /**
     * Deadline tasks, able to identify the supported date pattern: DD/MM/YYYY
     * @param description
     */
    public Deadline(String description){
        super(description.split("/by")[0]);
        String[] arg = description.split("/by", 2);
        String deadline = arg[1];
        try{
            String datePattern = "dd/MM/yyyy";
            Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
            Matcher matcher = pattern.matcher(arg[1]);
            if(matcher.find()){
                LocalDate date = LocalDate.parse(matcher.group(), DateTimeFormatter.ofPattern(datePattern));
                deadline = arg[1].replace(matcher.group(), date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            }
        } catch (Exception ignored) {}
        this.by = deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by:" + this.by + ')';
    }
}
