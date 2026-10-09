package com.wn74.invoice_compliance_engine.domain;

import java.time.Instant;
import java.util.UUID;

public class LogEntry {
    
    private UUID entryId = UUID.randomUUID();
    private Instant date = Instant.now();
    private UUID invoiceId;
    private String action;
    private String reason;
    private InvoiceStatus resultStatus;

    //JPA constructor
    public LogEntry(){

    }

    public LogEntry(UUID invoiceId,String action, String reason, InvoiceStatus resultStatus){

        this.invoiceId = invoiceId;
        this.action = action;
        this.reason = reason;
        this.resultStatus = resultStatus;

    }

    public UUID getEntryId() {
        return entryId;
    }
    public UUID getInvoiceId() {
        return invoiceId;
    }
    public Instant getDate() {
        return date;
    }
    public String getAction() {
        return action;
    }
    public String getReason() {
        return reason;
    }
    public InvoiceStatus getResultStatus() {
        return resultStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entryId == null) ? 0 : entryId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LogEntry other = (LogEntry) obj;
        if (entryId == null) {
            if (other.entryId != null)
                return false;
        } else if (!entryId.equals(other.entryId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LogEntry [entryId=" + entryId + ", date=" + date + ", invoiceId=" + invoiceId + ", action=" + action
                + ", reason=" + reason + ", resultStatus=" + resultStatus + "]";
    }

    
    

    
    

}
