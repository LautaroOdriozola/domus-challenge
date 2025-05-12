# Movie API

## Overview

This API allows you to fetch information about movies and their directors. You can filter directors based on the number of movies they have directed using the `/directors` endpoint.

## Endpoints

### `GET /api/movies/directors`

Get directors who have directed more than a specified number of movies.

**Query Parameters:**
- `threshold`: The minimum number of movies a director should have directed to be included in the response.

**Responses:**
- `200 OK`: Returns a list of directors who have directed more than the specified threshold.
- `400 Bad Request`: Returns an error message if the `threshold` is invalid or negative.

### Example:

```bash
curl "http://localhost:8080/api/movies/directors?threshold=3"
