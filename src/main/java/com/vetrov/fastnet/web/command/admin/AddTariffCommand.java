package com.vetrov.fastnet.web.command.admin;

import com.vetrov.fastnet.Path;
import com.vetrov.fastnet.db.entity.Tariff;
import com.vetrov.fastnet.db.services.ITariffService;
import com.vetrov.fastnet.db.services.TariffServiceImpl;
import com.vetrov.fastnet.web.command.ICommand;
import com.vetrov.fastnet.web.command.outofcontrol.LoginCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddTariffCommand implements ICommand {
    private static final org.apache.log4j.Logger log = Logger.getLogger(AddTariffCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name").trim();
        String price = request.getParameter("price").trim();
        String description = request.getParameter("description").trim();
        String serviceId = request.getParameter("serviceId");

        String resp = Path.COMMAND_MAIN;

        ITariffService service = new TariffServiceImpl();
        Tariff tariff = new Tariff();
        tariff.setName(name);
        tariff.setPrice(Double.parseDouble(price));
        tariff.setDescription(description);
        tariff.setServiceId(Long.parseLong(serviceId));

        service.save(tariff);
        log.info("Tariff was created");
        try {
            response.sendRedirect(resp);
            resp = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            resp = Path.PAGE_ERROR_PAGE;
            log.warn("Couldn't redirect to command page");
        }
        return resp;
    }
}
