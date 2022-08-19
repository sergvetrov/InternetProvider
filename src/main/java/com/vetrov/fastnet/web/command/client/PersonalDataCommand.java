package com.vetrov.fastnet.web.command.client;

import com.vetrov.fastnet.Path;
import com.vetrov.fastnet.db.entity.Account;
import com.vetrov.fastnet.db.entity.Tariff;
import com.vetrov.fastnet.db.entity.User;
import com.vetrov.fastnet.db.services.*;
import com.vetrov.fastnet.web.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;


public class PersonalDataCommand implements ICommand {

    private static final org.apache.log4j.Logger log = Logger.getLogger(PersonalDataCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User fullUser = (User) session.getAttribute("fullUser");

        IUserService userService = new UserServiceImpl();
        ITariffService tariffService = new TariffServiceImpl();
        IAccountService accountService = new AccountServiceImpl();

        String forward = Path.COMMAND_ACCOUNT;

        if (request.getParameterValues("arrTrafficsId") != null) {
            String[] trafficsId = request.getParameterValues("arrTrafficsId");
            userService.saveLinksUsersHasTariffs(fullUser, trafficsId);
            double oldBalance = fullUser.getAccount().getBalance();
            double result = 0;
            List<Tariff> tariffs = tariffService.findAll();
            for (Tariff tariff : tariffs) {
                for (String id : trafficsId) {
                    if (tariff.getId() == Long.parseLong(id)) {
                        result = oldBalance - tariff.getPrice();
                    }
                }
            }
            if (!fullUser.isBlocked() && result < 0) {
                fullUser.setBlocked(true);
                userService.update(fullUser);
            }
            Account account = fullUser.getAccount();
            account.setBalance(result);
            accountService.update(account);
            try {
                response.sendRedirect(forward);
            } catch (IOException e) {
                e.printStackTrace();
            }
            forward = Path.COMMAND_REDIRECT;
        }

        List<Tariff> internetTariffs = tariffService.findAllById(1);
        List<Tariff> iptvTariffs = tariffService.findAllById(2);
        List<Tariff> telephonyTariffs = tariffService.findAllById(3);

        request.setAttribute("internetTariffs", internetTariffs);
        request.setAttribute("iptvTariffs", iptvTariffs);
        request.setAttribute("telephonyTariffs", telephonyTariffs);

        // Sorting Internet tariffs
        if (request.getParameter("name_inet_asc") != null) {
            internetTariffs.sort(Comparator.comparing(Tariff::getName));
            forward = Path.PAGE_ACCOUNT;
            log.info("Internet tariffs sorted by name");
        }
        if (request.getParameter("name_inet_desc") != null) {
            internetTariffs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
            forward = Path.PAGE_ACCOUNT;
            log.info("Internet tariffs sorted by name");
        }
        if (request.getParameter("price_inet_asc") != null) {
            internetTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));
            forward = Path.PAGE_ACCOUNT;
            log.info("Internet tariffs sorted by price");
        }
        if (request.getParameter("price_inet_desc") != null) {
            internetTariffs.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
            forward = Path.PAGE_ACCOUNT;
            log.info("Internet tariffs sorted by price");
        }

        // Sorting TV tariffs
        if (request.getParameter("name_iptv_asc") != null) {
            iptvTariffs.sort(Comparator.comparing(Tariff::getName));
            forward = Path.PAGE_ACCOUNT;
            log.info("TV tariffs sorted by name");
        }
        if (request.getParameter("name_iptv_desc") != null) {
            iptvTariffs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
            forward = Path.PAGE_ACCOUNT;
            log.info("TV tariffs sorted by name");
        }
        if (request.getParameter("price_iptv_asc") != null) {
            iptvTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));
            forward = Path.PAGE_ACCOUNT;
            log.info("TV tariffs sorted by price");
        }
        if (request.getParameter("price_iptv_desc") != null) {
            iptvTariffs.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
            forward = Path.PAGE_ACCOUNT;
            log.info("TV tariffs sorted by price");
        }

        // Sorting Phone tariffs
        if (request.getParameter("name_phone_asc") != null) {
            telephonyTariffs.sort(Comparator.comparing(Tariff::getName));
            forward = Path.PAGE_ACCOUNT;
            log.info("Phone tariffs sorted by name");
        }
        if (request.getParameter("name_phone_desc") != null) {
            telephonyTariffs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
            forward = Path.PAGE_ACCOUNT;
            log.info("Phone tariffs sorted by name");
        }
        if (request.getParameter("price_phone_asc") != null) {
            telephonyTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));
            forward = Path.PAGE_ACCOUNT;
            log.info("Phone tariffs sorted by price");
        }
        if (request.getParameter("price_phone_desc") != null) {
            telephonyTariffs.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
            forward = Path.PAGE_ACCOUNT;
            log.info("Phone tariffs sorted by price");
        }
        return forward;
    }
}
