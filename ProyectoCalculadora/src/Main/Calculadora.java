package Main;
public class Calculadora {
	private char[] operadores;
	private String[] numeros;

	public Calculadora() {
		this.operadores = new char[50];
		this.numeros = new String[50];
		for (int i = 0; i < 50; i++) {
			operadores[i] = 'a';
		}
	}
	
	public boolean esValido(String calc) {
		calc.trim();
		//
		if (calc.isEmpty() || !esNum(calc.charAt(0)) || !esNum(calc.charAt(calc.length() - 1))) 
		{
			return false;
		}

		for (int i = 0; i < calc.length(); i++) {
			if (!esNum(calc.charAt(i))) {
				if (!esOper(calc.charAt(i))) {
					return false;

				}

			}
		}

		return true;

	}

	public void guardar(String calc, int guar) {

		for (int i = 0; i < calc.length(); i++) {
			// EL CARACTER ES UN NUMERO Y NO ESTAMOS AL FINAL DEL STRING
			// INGRESADO?..VUELVE A ENTRAR AL FOR CON i+1
			if (esNum(calc.charAt(i)) && i != calc.length() - 1) {
				continue;
				// SI EL CARACTER ES UN NUMERO PERO ESTAMOS AL FINAL QUIERE
				// DECIR QUE NO HAY MAS OPERADORES ENTONCES GUARDO EL NUMERO
			} else if (esNum(calc.charAt(i)) && i == calc.length() - 1) {
				numeros[guar] = calc;
				return;

			}
			// SI EL CARACTER ES OPERADOR GUARDO EL OPERADOR Y EL NUMERO Y CORTO
			// EL STRING Y VUELVO A LLAMAR AL METODO
			else if (esOper(calc.charAt(i))) {
				String num = calc.substring(0, i);
				numeros[guar] = num;
				char ope = calc.charAt(i);
				operadores[guar] = ope;
				calc = calc.substring(i + 1);
				guardar(calc, guar + 1);

			}
			return;
		}
		return;

	}

	public void reiniciar() {
		for (int i = 0; i < 50; i++) {
			operadores[i] = 'a';
			numeros[i] = "0";
		}

	}

	public String calcular(String calc, double resultado, int i) {

		int a = 0;
		int b = 0;
		// EL ARRAY ESTA LLENO DE a's, si llegue a una quiere decir que no hay
		// mas operadores
		if (operadores[i] == 'a') {
			String res = Double.toString( resultado);
			return res;

		}
		a = Integer.parseInt(numeros[i + 1]);
		char oper = operadores[i];
		// SI ES LA PRIMERA VEZ QUE ITERO AGARRO LOS DOS PRIMEROS NUMEROS
		if (i == 0) {
			b = Integer.parseInt(numeros[i]);
			switch (oper) {
			case '+':
				resultado += a + b;
				break;
			case '-':
				resultado += b - a;
				break;
			case '*':
				resultado= a*b;
				break;
			case '/':
				resultado= b/a;
				break;
			case '%':
				resultado= (a*b)/100;
				break;
			}
			// RECURSIVIDAD
			return calcular(calc, resultado, i + 1);
			// SI NO ES LA PRIMERA VEZ LE SUMO O RESTO EL SIGUIENTE NUMERO AL
			// OBTENIDO ANTERIORMENTE
		} else if (i > 0) {
			switch (oper) {
			case '+':
				resultado += a;
				break;
			case '-':
				resultado -= a;

				break;
			}

			return calcular(calc, resultado, i + 1);

		}

		return "algo salio mal";
	}

	public boolean esNum(char c) {
		if (Character.isDigit(c)) {
			return true;
		}

		return false;
	}

	public boolean esOper(char c) {
		if (c == '+' || c == '-'|| c == '/'|| c == '*'|| c == '%') {
			return true;
		}

		return false;
	}
}
