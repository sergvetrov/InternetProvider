package com.vetrov.fastnet.web.command.admin;

import com.vetrov.fastnet.Path;
import com.vetrov.fastnet.db.services.ITariffService;
import com.vetrov.fastnet.db.services.TariffServiceImpl;
import com.vetrov.fastnet.web.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RemoveTariffCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ITariffService service = new TariffServiceImpl();
        int tariffId = Integer.parseInt(request.getParameter("tariff_id"));
        service.remove(tariffId);
        return Path.COMMAND_MAIN;
    }
}
