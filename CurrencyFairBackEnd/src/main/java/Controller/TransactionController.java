package Controller;

import java.util.List;

import Model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Service.TransactionService;
import Service.TransactionServiceInterface;
import Exception.MessageDateFormatException;

@RestController
@RequestMapping("/")
public class TransactionController {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionServiceInterface transactionService;

    @PostMapping("/consumeMessage")
    public ResponseEntity consumeMessage(
            @RequestBody MessageCreateRequest messageCreateRequest
    ) {
        boolean flag = false;
        Message message = new Message();
        try {
            System.out.println();
            try {
                message = Message.toMessage(messageCreateRequest);
            } catch (MessageDateFormatException e) {
                LOG.info("Date Format was not as expected." + e.getMessage());
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            flag = transactionService.insertMessageUnit(message);
        } catch (Exception e) {
            LOG.info("Message insertion failed. Error Message:" + e.getMessage());
        }
        if (flag) {
            return new ResponseEntity<String>("Message was successfully inserted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Sorry the message could not be inserted", HttpStatus.OK);
        }
    }

    @CrossOrigin
    @GetMapping("/getMessages")
    public ResponseEntity getAllMessages() {
        try {
            List<Message> messageList = transactionService.getAllMessages();
            if (messageList == null) {
                return new ResponseEntity<String>("Something went wrong couldnt fetch the messages", HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin
    @GetMapping("/getMessagesOnCurrency/{currency}/{toOrFrom}")
    public ResponseEntity getMessagesFilteredByCurrency(
            @PathVariable("currency") String currency,
            @PathVariable("toOrFrom") String toOrFrom
    ) {
        try {
            List<Message> messageList = transactionService.getAllMessagesBasedOnCurrency(currency, toOrFrom);
            if (messageList == null) {
                return new ResponseEntity<String>("Something went wrong couldnt fetch the messages", HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/getMessagesOnOriginatingCountry/{country}")
    public ResponseEntity getMessagesFilteredByOriginatingCountry(
            @PathVariable("country") String countryFilter
    ) {
        try {
            List<Message> messageList = transactionService.getAllMessagesBasedOnOriginatingCounty(countryFilter);
            if (messageList == null) {
                return new ResponseEntity<String>("Something went wrong couldnt fetch the messages", HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/getMessagesFilter")
    public ResponseEntity getMessagedBasedOnFilter(
            @RequestParam(value = "currencyFrom", required = false) String currencyFrom,
            @RequestParam(value = "currencyTo", required = false) String currencyTo,
            @RequestParam(value = "originatingCountry", required = false) String originatingCountry
    ) {
        try {
            List<Message> messageList = transactionService.getMessagesBasedOnFilter(currencyFrom, currencyTo, originatingCountry);
            return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/getMessagesInAMinuteWindow")
    public ResponseEntity getMessagesInAMintueWindow(
            @RequestParam(value = "currencyFrom", required = false) String currencyFrom,
            @RequestParam(value = "currencyTo", required = false) String currencyTo,
            @RequestParam(value = "originatingCountry", required = false) String originatingCountry
    ) {
        try {
            List<Message> messageList = transactionService.getMessages60SecondTimeWindow(currencyFrom, currencyTo, originatingCountry);
            return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/getMessagesInAMinuteWindowStatistics")
    public ResponseEntity getMessagesInAMintueWindowStatistics(
            @RequestParam(value = "currencyFrom", required = false) String currencyFrom,
            @RequestParam(value = "currencyTo", required = false) String currencyTo,
            @RequestParam(value = "originatingCountry", required = false) String originatingCountry
    ) {
        try {
            WindowStatistics windowStatistics =
                    transactionService.getMessages60SecondTimeWindowStatistics(
                            currencyFrom,
                            currencyTo,
                            originatingCountry
                    );
            return new ResponseEntity<WindowStatistics>(windowStatistics, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/getDataForBarGraphCurrencyCount")
    public ResponseEntity getDataForGraph() {
        try {
            List<LabelData> dataList =
                    transactionService.getDataForGraph();
            return new ResponseEntity<List<LabelData>>(dataList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/getDataForLineGraph")
    public ResponseEntity getDataForLine() {
        try {
            List<LabelData> dataList =
                    transactionService.getDataForLine();
            return new ResponseEntity<List<LabelData>>(dataList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/getDataForPieChart")
    public ResponseEntity getDataForPieChart() {
        try {
            List<LabelData> dataList =
                    transactionService.getDataForPieChart();
            return new ResponseEntity<List<LabelData>>(dataList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Message fetch failed. Error Message:" + e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
