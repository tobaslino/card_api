package com.tobias.entity;

import java.math.BigDecimal;

public enum TransactionType {
    INPUT {
        @Override
        public void doTransactionOperation(Usuario user, BigDecimal value) {
            // TODO: Implementar as transações

        }
    },
    OUTPUT {
        @Override
        public void doTransactionOperation(Usuario user, BigDecimal value) {
            // TODO: Implementar as transações

        }
    };
    public abstract void doTransactionOperation(Usuario user, BigDecimal value);
}