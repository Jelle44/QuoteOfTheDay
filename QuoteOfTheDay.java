package nl.sogyo.javaopdrachten;

import java.time.LocalDate;

public class QuoteOfTheDay {

    String[][] quotes = {
        {"galileo", "eppur si muove"},
        {"archimedes", "eureka!"},
        {"erasmus", "in regione caecorum rex est luscus"},
        {"socrates", "I know nothing except the fact of my ignorance"},
        {"rené descartes", "cogito, ergo sum"},
        {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
    };

    public static void main(String[] args) {
        QuoteOfTheDay QuoteList = new QuoteOfTheDay();
        String[][] listOfQuotes = QuoteList.createArray();

        String author = "null";
        String quote = "null";
        
        LocalDate date = LocalDate.now();
        int numberOfQuote = listOfQuotes.length;
        
        printQuoteOfTheDay(author, quote, date, numberOfQuote, listOfQuotes);
    }

    private static void printQuoteOfTheDay(String author, String quote, LocalDate date, int numberOfQuote, String[][] listOfQuotes) {
        numberOfQuote = selectQuote(date.getDayOfMonth(), numberOfQuote);
        System.out.println("The quote for " + capitaliseNames(date.getDayOfWeek().name()) + " the " + date.getDayOfMonth() + "th of " + capitaliseNames(date.getMonth().name()) + " is:");
        printQuote(quote, listOfQuotes, numberOfQuote);
        checkPunctuation(listOfQuotes, numberOfQuote);
        printName(author, listOfQuotes, numberOfQuote);
    }

    private String[][] createArray() {
        String[][] quotes2 = quotes;
        return quotes2;
    }

    private static int selectQuote(int dayOfMonth, int numberOfQuote) {
        numberOfQuote = (dayOfMonth % numberOfQuote) -1;
        if(numberOfQuote < 0){
            numberOfQuote++;
        }
        return numberOfQuote;
    }

    private static void checkPunctuation(String[][] quotes, int num) {
        if(!quotes[num][1].matches(".*\\p{Punct}")){
            System.out.print(".");
        }
    }

    private static void printName(String author, String[][] quotes, int num) {
        System.out.println(splitNames(author, quotes, num));
    }

    private static String capitaliseQuote(String quote, String[][] quotes, int num) {
        quote = quotes[num][1];
        String firstLetter = quote.substring(0, 1).toUpperCase();
        String restOfString = quote.substring(1);
        quote = firstLetter + restOfString;
        return quote;
    }

    private static void printQuote(String quote, String[][] quotes, int num) {
        System.out.print("\"" + capitaliseQuote(quote, quotes, num));
    }

    private static String splitNames(String author, String[][] quotes, int num) {
        String[] parts = quotes[num][0].split(" ");
        String part1 = parts[0];
        part1 = capitaliseNames(part1);

        try{
            String part2 = parts[1];
            part2 = capitaliseNames(part2);
            
            try{
                String part3 = parts[2];
                part3 = capitaliseNames(part3);
                author = "\" -- " + part1 + " " + part2 + " " + part3;
            } catch(Exception e){
                author = "\" -- " + part1 + " " + part2;
            }   
        } catch(Exception e){
            author = "\" -- " + part1;
        }
        return author;
    }

    public static String capitaliseNames(String part){
        String firstLetter = part.substring(0, 1).toUpperCase();
        String restOfString = part.substring(1).toLowerCase();
        part = firstLetter + restOfString;
        return part;
    }
}