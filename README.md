
# Horus recruitment task
Application developed for Horus company as a recruitment task for Junior Java Developer role.




## Authors

- [@Dletkiewicz](https://www.github.com/dletkiewicz)



## Features

- Listing blocks by material
- Retrieving block by color
- Counting blocks(with nested blocks included)

## Tech Stack

Java, Spring Boot, Lombok, Maven, JUnit5



## Deployment

Download and unzip the source repository, or clone it using Git:
 git clone https://github.com/Dletkiewicz/horusv1.git

and then click
```bash
  CTRL + SHIFT + F10
```
or type this command in terminal
```bash
mvnw spring-boot:run
```

## Documentation

The main challenge in this task is that a CompositeBlock can consist of multiple Blocks, and this should be taken into account when listing, searching by color, or counting them.
