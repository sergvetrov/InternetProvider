package com.vetrov.fastnet.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 */
public interface ICommand {
    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
