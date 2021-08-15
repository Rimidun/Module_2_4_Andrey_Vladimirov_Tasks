package project.servlets;

import project.ObjectFactory;
import project.entity.Event;
import project.service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EventServlet extends HttpServlet {
    private MainService mainService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        Long userId = null;
        Long fileId = null;

        try{
            userId = Long.parseLong(req.getParameter("userid"));
            fileId = Long.parseLong(req.getParameter("fileid"));
        }catch (NumberFormatException e){
            resp.setStatus(502);
            throw new Error("Invalid id on file ");
        }

        List<Event> events = mainService.getEventList(userId, fileId);
        StringBuilder sb = new StringBuilder();
        for (Event event : events) {
            sb.append("Create: ").append(event.getTime()).append("<br>")
                    .append("UserId: ").append(userId).append("<br>")
                    .append("FileId: ").append(fileId).append("<br>")
                    .append("<hr>").append("\n");
        }
        pw.println(HtmlParts.page("Event print page", sb.toString()));
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        this.mainService = ObjectFactory.getObjectFactory().getMainService();
    }
}
