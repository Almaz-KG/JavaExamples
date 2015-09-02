package art.java.javamail;

import javax.mail.Address;
import javax.mail.Message;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagesTableModel extends AbstractTableModel{
    private static final String[] columnNames = {"Sender", "Subject", "Date"};
    private List<Message> messageList = new ArrayList<>();

    @Override
    public int getRowCount() {
        return messageList.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            Message message = messageList.get(rowIndex);
            switch (columnIndex){
                case 0:
                    Address[] senders = message.getFrom();
                    if(senders != null || senders.length > 0)
                        return senders[0].toString();
                    return "[none]";
                case 1:
                    String subject = message.getSubject();
                    if(subject != null && subject.length() > 0)
                        return subject;
                    return "[none]";
                case 2:
                    Date date = message.getSentDate();
                    if(date != null)
                        return date.toString();
                    return "[none]";
            }
        } catch (Exception e){
            return "";
        }
        return "";
    }

    public List<Message> getMessageList() {
        return messageList;
    }
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
    public void setMessages(Message[] messages) {
        if(messages != null) {
            for (Message message : messages)
                messageList.add(message);
            fireTableDataChanged();
        }
    }
    public Message getMessage(int index){
        return messageList.get(index);
    }
    public void deleteMessage(int index){
        messageList.remove(index);
        fireTableRowsDeleted(index, index);
    }








}
