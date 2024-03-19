check:
	@echo "Checking the status of the Spring-boot app:"
	@ps aux | grep spring | grep -v grep | awk '{ print $$1, $$2, $$3, $$4, $$11 }'

start:
	./mvnw spring-boot:run -e

bg-start:
	./mvnw spring-boot:run -e >/dev/null 2>&1 &

shutdown:
	@echo "-------Spring-boot application shutting down-------"
	@pids=$$(ps aux | grep spring | grep -v grep | awk '{ print $$2 }'); \
	if [ -n "$$pids" ]; then \
        echo "Killing processes: $$pids"; \
        kill -9 $$pids; \
        echo "App shutdown complete."; \
	else \
		echo "No running processes found."; \
	fi