.PHONY: help build test
.DEFAULT_GOAL := help

# Build the project
build:
	@gradlew build

# Clean the project
clean:
	@gradlew clean

# Run tests
test:
	@gradlew test

# Generate Javadoc
doc:
	@gradlew generateOpenApiDocs

# Run the project
run:
	@gradlew bootRun

# Run SonarQube analysis
sonar:
	@gradlew sonar

# Build and push Docker image using Jib
jib:
	@gradlew jib

# Show help
help:
	@echo ""
	@echo "Usage:"
	@echo "    make [target]"
	@echo ""
	@echo "Targets:"
	@awk '/^[a-zA-Z\-_0-9]+:/ \
	{ \
		helpMessage = match(lastLine, /^# (.*)/); \
		if (helpMessage) { \
			helpCommand = substr($$1, 0, index($$1, ":")-1); \
			helpMessage = substr(lastLine, RSTART + 2, RLENGTH); \
			printf "\033[36m%-22s\033[0m %s\n", helpCommand,helpMessage; \
		} \
	} { lastLine = $$0 }' $(MAKEFILE_LIST)
