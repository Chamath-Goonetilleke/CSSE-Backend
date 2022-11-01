package com.csse.backend.RetrieveDeliveredItems.Common;

public class CommonConstants {

    /**
     * Response Messages
     */
    public static final String SOMETHING_WENT_WRONG = "Something went wrong";
    public static final String SAVED = " saved successfully";
    public static final String NO_DATA_FOUND = "No data found";

    /**
     * URLs for CreditCardController
     */
    public static final String CREDIT_CARD_BASE_URL = "/api/v1/payment";
    public static final String ADD_CARD_URL = "/addCard";
    public static final String Get_ALL_USER_CARDS = "/getAllCards/{userId}";
    public static final String GET_CARD_BY_ID = "/getCardById/{id}";
    public static final String SAVE_PAYMENT = "/savePayment";

    /**
     * URLs for DeliveredItemsController
     */
    public static final String DELIVERED_ITEMS_BASE_URL = "/api/v1/deliveredItems";
    public static final String GET_ALL_ORDERS = "/getAllOrders";
    public static final String SAVE_REPORT = "/saveReport";

    /**
     * URLs for MessageController
     */
    public static final String MESSAGE_BASE_URL = "/api/v1/message";
    public static final String SAVE_MESSAGE = "/addMessage";

    /**
     * Column Names and DB Name of CreditCard
     */
    public static final String CREDIT_CARD_DB_NAME = "credit_card";
    public static final String CREDIT_CARD_ID = "id";
    public static final String CREDIT_CARD_USER_ID = "user_id";
    public static final String CREDIT_CARD_NUMBER = "card_number";
    public static final String CREDIT_CARD_HOLDER_NAME = "card_holder_name";
    public static final String CREDIT_CARD_EXPIRY_DATE = "expiry_date";
    public static final String CREDIT_CARD_CVC = "cvc";

    /**
     * Column Names and DB Name of Message
     */
    public static final String MESSAGE_DB_NAME = "message";
    public static final String MESSAGE_ID = "id";
    public static final String MESSAGE_BODY = "message";
    public static final String MESSAGE_SENDER = "sender";
    public static final String MESSAGE_RECEIVER = "receiver";
    public static final String MESSAGE_ORDER_REFERENCE = "order_reference";

    /**
     * Column Names and DB Name of Payment
     */
    public static final String PAYMENT_DB_NAME = "payment";
    public static final String PAYMENT_ID = "id";
    public static final String PAYMENT_USER_ID = "user_id";
    public static final String PAYMENT_REFERENCE_NUMBER = "reference_number";
    public static final String PAYMENT_TOTAL_AMOUNT = "total_amount";
    public static final String PAYMENT_CARD_ID = "card_id";

    /**
     * Column Names and DB Name of Report
     */
    public static final String REPORT_DB_NAME = "report";
    public static final String REPORT_ID = "id";
    public static final String REPORT_DATE = "date";
    public static final String REPORT_IMAGE = "image";

    /**
     * SQL queries in CreditCardRepositoryImpl
     */
    public static final String GET_ALL_CARDS_QUERY = " SELECT c FROM CreditCard c WHERE c.userId = :userId ";
    public static final String USER_ID_PARAM = "userId";

    /**
     * SQL queries in CreditCardRepositoryImpl
     */
    public static final String GET_ALL_DELIVERED_ITEMS = "SELECT o FROM Item o ";


}
