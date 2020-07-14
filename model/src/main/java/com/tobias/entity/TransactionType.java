package com.tobias.entity;

import java.math.BigDecimal;

public enum TransactionType {
    INPUT {
        @Override
        public void doTransactionOperation(User user, BigDecimal value) {
            // TODO: Implementar as transações

        }
    },
    OUTPUT {
        @Override
        public void doTransactionOperation(User user, BigDecimal value) {
            // TODO: Implementar as transações

        }
    };
    public abstract void doTransactionOperation(User user, BigDecimal value);
}