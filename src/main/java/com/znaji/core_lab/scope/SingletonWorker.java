package com.znaji.core_lab.scope;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class SingletonWorker {

        private final ObjectProvider<PrototypeAudit> auditProvider;

        public SingletonWorker(ObjectProvider<PrototypeAudit> auditProvider) {
                this.auditProvider = auditProvider;
        }

        public void doWork(String input) {
                PrototypeAudit audit = auditProvider.getObject();
                audit.record("Singleton handled " + input);
        }

        @Lookup // TODO: Compare ObjectProvider vs @Lookup by implementing this method.
        public PrototypeAudit lookupAudit() {
                throw new IllegalStateException("Spring overrides @Lookup methods at runtime");
        }
}
