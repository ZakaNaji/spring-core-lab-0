# Spring Core Deep-Dive Lab

Hands-on lab for exploring advanced Spring Framework core concepts using Spring Boot. Each module
contains partially implemented components, `// TODO:` markers, and tests to guide you through
real-world scenarios.

## Prerequisites

* Java 21
* Maven 3.9+

## Running the application

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Profiles demonstrate different wiring options (`dev` uses email notifications, `prod` switches to
SMS). Try toggling profiles to observe bean selection differences.

## Running the tests

```bash
./mvnw test
```

Some tests are guarded by assumptions and will only run after you flip the corresponding switches
(as indicated in the TODO comments). Others fail until you complete the exercises.

## Exercises Overview

Work through the files listed below. Each file contains TODO comments explaining the change you need
to make. The recommended order matches the progression of Spring Core topics:

1. **IoC & Dependency Injection**
   * `service/LegacyReportGenerator.java` – refactor setter injection into constructor injection.
   * `service/RetryingPaymentGateway.java` – replace the simulated retry with deterministic logic.
   * `service/PaymentGateway.java` – provide a concrete implementation instead of throwing
     `UnsupportedOperationException`.
   * `service/CircularAService.java` & `service/CircularBService.java` – experiment with `@Lazy` and
     setter injection to resolve circular dependencies.
2. **Bean Scopes**
   * `scope/SingletonWorker.java` – compare `ObjectProvider` and `@Lookup` for prototype lookups.
   * `scope/ThreadScope.java` – finish destruction callbacks and contextual resolution.
   * Enable the thread-scope test via the `runThreadScopeExercise` system property once implemented.
3. **Profiles & Conditionals**
   * `config/ProfiledDataSourceConfig.java` – plug in realistic datasource URLs per profile.
   * `service/SmsNotificationService.java` – simulate a production SMS integration.
4. **Bean Lifecycle**
   * Observe logs from `lifecycle/LifecycleLogger.java` and extend the `TimingBeanPostProcessor` to
     capture bean initialization metrics.
5. **Factory & Special Beans**
   * `factory/TimestampFactoryBean.java` – experiment with singleton vs prototype factories.
6. **Events**
   * `events/OrderPublisher.java` – enrich the published event with correlation IDs.
7. **AOP**
   * Modify `aop/AuditAspect.java` to include custom metrics and see the self-invocation gotcha in
     `service/SelfInvocationDemoService.java`.
8. **Resources & Properties**
   * `resources/ResourceLoadingService.java` – handle missing resources and differentiate between
     filesystem and classpath lookups.
9. **SpEL & Configuration**
   * Adjust `config/PricingProperties.java` to compose expressions with Environment values.
10. **Context Lifecycle & Testing**
    * Explore test caching by introducing `@DirtiesContext` and measure the impact on execution time.

## ⚠️ Gotchas to Explore

* What happens if you remove `@Lazy` in `CircularAService`? Why does the context fail to start?
* Inject a prototype bean into a singleton without `ObjectProvider` – watch stale state leak across
  invocations.
* Forget to pass a `BindingResult` when using `OrderFormValidator` in a controller. Spring will throw
  a `BindException` before your method executes.
* Annotate `SelfInvocationDemoService.internalWork()` with `@Transactional` or `@Async` and call it
  from `outerCall()` – the advice is skipped because of self-invocation.
* Mark `FinalMethodService.finalMethod()` with advice: CGLIB proxies cannot override final methods.
* Enable the `prod` profile but forget to define `connectionDescription` – observe how missing beans
  manifest at startup.
* Use `@DirtiesContext` on every test class and measure the slowdown due to context recreation.
* Disable the `TimingBeanPostProcessor` and note the loss of lifecycle logging.

## Suggested Workflow

1. Read the TODO comments in each file and make an initial attempt.
2. Run the focused tests (found under `src/test/java`) after each section.
3. Re-run the full test suite and the application to confirm behaviour.
4. Toggle profiles (`dev`, `prod`) and system properties (like
   `-DrunThreadScopeExercise=true`) to experiment with the optional parts.

Happy hacking!
