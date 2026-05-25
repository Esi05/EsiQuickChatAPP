/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MakaB
 */
public class MessageTest {
    
    public MessageTest() {
        import com.mycompany.chatapppart1.Messages.Message;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MakaB
 */
public class MessageTest {

    // Reset the static message counter before each test so tests don't interfere
    @Before
    public void setUp() {
        Message.resetTotalMessages();
    }

    // -------------------------------------------------------
    // checkMessageLength tests
    // -------------------------------------------------------

    /**
     * Test message length valid — message under 250 characters
     */
    @Test
    public void testMessageLengthValid() {
        Message msg = new Message();
        msg.setMessageText("Hello Mike");
        String expected = "Message ready to send.";
        assertEquals(expected, msg.checkMessageLength());
    }

    /**
     * Test message length invalid — message over 250 characters
     */
    @Test
    public void testMessageLengthInvalid() {
        Message msg = new Message();
        String longMessage = "a".repeat(260);
        msg.setMessageText(longMessage);
        String expected = "Message exceeds 250 characters by 10, please reduce size.";
        assertEquals(expected, msg.checkMessageLength());
    }

    // -------------------------------------------------------
    // checkRecipientCell tests
    // -------------------------------------------------------

    /**
     * Test valid recipient — +27718693002 (has international code)
     */
    @Test
    public void testRecipientNumberValid() {
        Message msg = new Message();
        msg.setRecipient("+27718693002");
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    /**
     * Test invalid recipient — 08575975889 (no international code)
     */
    @Test
    public void testRecipientNumberInvalid() {
        Message msg = new Message();
        msg.setRecipient("08575975889");
        String expected = "Cell phone number is incorrectly formatted or does not contain international code.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    // -------------------------------------------------------
    // createMessageHash tests
    // -------------------------------------------------------

    /**
     * Test message hash — Test Data Message 1
     * MessageID: "0012345678", messageNumber: 0
     * Text: "Hi Mike, can you join us for dinner tonight?"
     * Expected: "00:0:HITONIGHT"
     */
    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message();
        msg.setMessageID("0012345678");
        msg.setMessageNumber(0);
        msg.setMessageText("Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    /**
     * Test message hash — Test Data Message 2
     * MessageID: "0012345678", messageNumber: 1
     * Text: "Hi Keenan, did you receive the payment?"
     * Expected: "00:1:HIPAYMENT"
     */
    @Test
    public void testMessageHashCorrectMessage2() {
        Message msg = new Message();
        msg.setMessageID("0012345678");
        msg.setMessageNumber(1);
        msg.setMessageText("Hi Keegan, did you receive the payment?");
        assertEquals("00:1:HIPAYMENT", msg.createMessageHash());
    }

    // -------------------------------------------------------
    // generateMessageID tests
    // -------------------------------------------------------

    /**
     * Test message ID is created — not null and exactly 10 digits
     */
    @Test
    public void testMessageIDCreated() {
        Message msg = new Message();
        String id = msg.generateMessageID();
        assertNotNull(id);
        assertEquals(10, id.length());
    }

    // -------------------------------------------------------
    // sentMessage tests
    // -------------------------------------------------------

    /**
     * Test sent message — user selects Send
     */
    @Test
    public void testSentMessage() {
        Message msg = new Message();
        assertEquals("Message successfully sent.", msg.sentMessage("Send"));
    }

    /**
     * Test disregard message — user selects Disregard
     */
    @Test
    public void testDisregardMessage() {
        Message msg = new Message();
        assertEquals("Press 0 to delete the message.", msg.sentMessage("Disregard"));
    }

    /**
     * Test store message — user selects Store
     */
    @Test
    public void testStoreMessage() {
        Message msg = new Message();
        assertEquals("Message successfully stored.", msg.sentMessage("Store"));
    }

    // -------------------------------------------------------
    // returnTotalMessages test
    // -------------------------------------------------------

    /**
     * Test total messages — create 2 messages and verify counter equals 2
     */
    @Test
    public void testReturnTotalMessages() {
        new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals(2, Message.getTotalMessages());
    }
}
    }
    
}
