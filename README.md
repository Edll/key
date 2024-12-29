# Word Map Test Project

## start backend test

```
cd ./backend/
mvn clean install
cd ./app/target
java -jar app-0.0.1-SNAPSHOT.jar
```

## start frontend test

```
cd ./frontend
npm install
npm start
```

localhost:3000

it is important that, if the application only runs locally, everything goes via localhost. Otherwise the browser will not allow access because of cors.

## properties

### Wordpress API

```
de.edlly.key.wordpress.api.endpoint=https://thekey.academy/wp-json/wp/v2/posts
```

## Change backend endpoint or port

change variable WS_ENDPOINT in index.tsx
