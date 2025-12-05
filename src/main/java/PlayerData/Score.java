/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlayerData;

/**
 *
 * @author eboni
 */
public class Score {
    private int exitosos;
    private int fallidos;
    private int muertes;
    private int victorias;
    private int derrotas;
    private int rendiciones;

    public void registrarAtaque(int daño) {
        if (daño > 100) exitosos++;
        else fallidos++;
    }

    public void registrarMuerte() { muertes++; }
    public void registrarVictoria() { victorias++; }
    public void registrarDerrota() { derrotas++; }
    public void registrarRendicion() { rendiciones++; }

    @Override
    public String toString() {
        return "Score [Exitosos=" + exitosos + ", Fallidos=" + fallidos +
               ", Muertes=" + muertes + ", Victorias=" + victorias +
               ", Derrotas=" + derrotas + ", Rendiciones=" + rendiciones + "]";
    }
}

