package ex03;

import java.util.UUID;

public interface TransactionsList {
    void addLast(Transaction data);

    void addFirst(Transaction data);

    void remove(UUID idTransaction);

    Transaction[] toArray();
}
