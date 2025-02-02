.DEFAULT_GOAL := build-run

run-dist:
	./build/install/calorie_counter/bin/calorie_counter

build:
	./gradlew build

run:
	./gradlew run

clean:
	./gradlew clean

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

build-run: build run

.PHONY: build