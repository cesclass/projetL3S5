package exchange;

public enum ComType {
    ERROR_WITHOUT_REASON,
    ERROR_ALREADY_CONNECTED,
    ERROR_INVALID_REQUEST,

    CONNECT_RQ,
    CONNECT_OK,
    CONNECT_KO,

    DISCONNECT_CLI,
    DISCONNECT_SRV,
    
    GROUPS_RQ,
    GROUPS_OK,
    GROUPS_KO,
    
    TICKETS_LIST_RQ,
    TICKETS_LIST_OK,
    TICKETS_LIST_KO,
    
    TICKET_RQ,
    TICKET_OK,
    TICKET_KO,
    
    NEW_TICKET_CLI,
    NEW_TICKET_SRV,
    NEW_TICKET_OK,
    NEW_TICKET_KO,

    NEW_MESSAGE_CLI,
    NEW_MESSAGE_SRV,
    NEW_MESSAGE_OK,
    NEW_MESSAGE_KO,
    
    STATUSES_RQ,
    STATUSES_OK,
    STATUSES_KO,

    EV_MESSAGE_STATUS
}