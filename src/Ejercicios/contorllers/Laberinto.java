package Ejercicios.contorllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Ejercicios.models.Celda;

/*
 * Un jugador está en la esquina superior izquierda (0,0) de un tablero m x n. En el tablero hay celdas
 * transitables (true) y no transitables (false). Encuentra un camino válido para ir a la esquina
 * inferior izquierda con el jugador, sabiendo que solo se puede mover hacia abajo y hacia la derecha.
 *
 * Ejemplo 1:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,false,false,true]
 *      [true,true,false,true]
 *      [true,true,false,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (0,2), (0,3), (1,3), (2,3), (3,3)]
 *
 * Ejemplo 2:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,true,true,true]
 *      [true,true,false,false]
 *      [true,true,true,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (1,1), (2,1), (3,1), (3,2), (3,3)]
 *
 */
public class Laberinto {

    public List<Celda> getPath(boolean[][] grid) {
        List<Celda> path = new ArrayList<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0 || !grid[0][0]) {
            return path;
        }

        // Map para almacenar si ya visitamos una celda y si es parte del camino
        Map<Celda, Boolean> cache = new HashMap<>();
        if (getPath(grid, 0, 0, path, cache)) {
            return path;
        }
        return new ArrayList<>();
    }

    private boolean getPath(boolean[][] grid, int row, int col, List<Celda> path, Map<Celda, Boolean> cache) {
        if (row >= grid.length || col >= grid[0].length || !grid[row][col]) {
            return false;
        }

        Celda point = new Celda(row, col);
        if (cache.containsKey(point)) {
            return cache.get(point);
        }

        boolean isAtEnd = (row == grid.length - 1) && (col == grid[0].length - 1);
        boolean success = false;

        if (isAtEnd || getPath(grid, row, col + 1, path, cache) || getPath(grid, row + 1, col, path, cache)) {
            path.add(point);
            success = true;
        }

        cache.put(point, success);
        return success;
    }
}