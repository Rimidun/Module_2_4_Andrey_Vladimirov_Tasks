package project.servlets;

public class HtmlParts {
    public static String head(String title) {
        return "        <!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>" + title + "</title>\n" +
                "</head>\n";
    }

    public static String bodyStart(){
        return "<body >\n";
    }

    public static String bodyEnd(){
        return "</body >\n";
    }

    public static String footer(){
        return "</html >\n";
    }

    public static String body(String bodyText){
        return bodyStart() + bodyText + bodyEnd();
    }

    public static String page(String title, String bodyText){
        return head(title) + body(bodyText) + footer();
    }
}
