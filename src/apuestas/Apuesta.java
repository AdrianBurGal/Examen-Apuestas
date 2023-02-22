
package apuestas;


public class Apuesta {

    public int getGoles_local() {
        return goles_local;
    }

    public void setGoles_local(int goles_local) {
        this.goles_local = goles_local;
    }

    public int getGoles_visitante() {
        return goles_visitante;
    }

    public void setGoles_visitante(int goles_visitante) {
        this.goles_visitante = goles_visitante;
    }

    public int getApostado() {
        return apostado;
    }

    public void setApostado(int apostado) {
        this.apostado = apostado;
    }

    private int dinero_disp;
    private int goles_local;
    private int goles_visitante;
    private int apostado;

    /**
     * Constructor
     */
    public Apuesta() {
    }

    /**
     * Constructor con parámetros
     * 
     * @param dinero_disp       Dinero disponible
     * @param goles_local       Goles del equipo local
     * @param goles_visitante   Goles del equipo visitante
     */
    public Apuesta(int dinero_disp, int goles_local, int goles_visitante) {
        this.dinero_disp = dinero_disp;
        this.goles_local = goles_local;
        this.goles_visitante = goles_visitante;
        this.apostado = 0;
    }
   
    /**
     * Método para obtener el valor del atributo dinero_disp
     * 
     * @return Devuelve el dinero disponible
     */
    public int getDinero_disp() {
        return dinero_disp;
    }

    /**
     * Método para modificar el valor del atributo dinero_disp
     * 
     * @param dinero_disp Devuelve el dinero disponible
     */
    public void setDinero_disp(int dinero_disp) {
        this.dinero_disp = dinero_disp;
    }

    /**
     * Método para apostar, permitiendo elegir la cantidad a apostar, no pudiendo ser inferior a 1 ni superior a tu saldo disponible
     * 
     * @param dinero Cantidad de dinero que se va a apostar
     * @throws Exception 
     */
    public void apostar(int dinero) throws Exception {
        if (dinero <= 0) {
            throw new Exception("No se puede apostar menos de 1€");
        }

        if (dinero > getDinero_disp()) {
            throw new Exception("No se puede apostar mas de lo que tienes");
        }
        {
            setDinero_disp(dinero - getDinero_disp());
            setApostado(dinero);
        }
    }

    /**
     * Método que comprueba si se ha acertado el resultado del partido
     * En caso de que lo haya acertado devuelve true. Chequea que no se metan menos de 0 goles
     * 
     * @param local     Resultado del equipo local
     * @param visitante Resultado del equipo visitante
     * @return          Devuelve si se ha acertado o no el resultado (true/false)
     * @throws Exception 
     */
    public boolean comprobar_resultado(int local, int visitante) throws Exception {
        boolean acertado = false;
        if ((local < 0) || (visitante) < 0) {
            throw new Exception("Un equipo no puede meter menos de 0 goles, por malo que sea");
        }

        if (getGoles_local() == local && getGoles_visitante() == visitante) {
            acertado = true;
        }
        return acertado;
    }

    /**
     * Método para cobrar la apuesta.
     * Comprueba que se acertó el resultado y, en ese caso, añade el valor 
     * apostado multiplicado por 10 al saldo disponible
     * 
     * @param cantidad_goles_local   Total de goles del equipo local
     * @param cantidad_goles_visit   Total de goles del equipo visitante
     * @throws Exception 
     */
    void cobrar_apuesta(int cantidad_goles_local, int cantidad_goles_visit) throws Exception {

        if (comprobar_resultado(cantidad_goles_local, cantidad_goles_visit) == false) {
            throw new Exception("No se puede cobrar una apuesta no acertada");
        }
        setDinero_disp(getDinero_disp() * 10);

    }
}