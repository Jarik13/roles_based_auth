package com.example.role_based_auth.controller;

import com.example.role_based_auth.service.DefaultUserService;
import com.example.role_based_auth.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private DefaultUserService userService;

    @Autowired
    private TicketService ticketService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("{id}/all")
    public ResponseEntity<?> getAllUserTickets(@PathVariable Long id) {
        logger.info("Request received to /api/tickets/{}/all", id);
        try {
            logger.debug("Fetching all user tickets with id: {}", id);
            return new ResponseEntity<>(ticketService.getAllUserTickets(id), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with id: {}", id);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while getting all user tickets id: {}", id);
            return new ResponseEntity<>("Unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserTicketById(@PathVariable(name = "id") Long userId,
                                               @RequestParam(name = "ticket") Long ticketId) {
        logger.info("Request received to /api/tickets/{}?ticket={}", userId, ticketId);
        try {
            logger.debug("Fetching user ticket with id: {}", ticketId);
            return new ResponseEntity<>(ticketService.getTicketById(userId, ticketId), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with id: {} while get user ticket with id: {}", userId, ticketId);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            logger.error("Ticket not found while getting user ticket with id: {}", ticketId);
            return new ResponseEntity<>("Ticket not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while getting user ticket!");
            return new ResponseEntity<>("Unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
