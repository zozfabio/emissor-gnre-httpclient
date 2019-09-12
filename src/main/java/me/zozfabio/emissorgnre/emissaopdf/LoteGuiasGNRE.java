package me.zozfabio.emissorgnre.emissaopdf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;

class LoteGuiasGNRE {

    private LoteGuiasGNRERegistro0 registro0;

    private List<LoteGuiasGNRERegistro1> registros1 = new LinkedList<>();

    private LoteGuiasGNRERegistro9 registro9;

    LoteGuiasGNRE(String lote) {
        Arrays.stream(lote.split("\\n"))
                .forEach(registro -> {
                    int tipo = Integer.parseInt(registro.substring(0, 1));
                    if (tipo == 0) {
                        registro0 = new LoteGuiasGNRERegistro0(registro);
                    } else if (tipo == 1) {
                        registros1.add(new LoteGuiasGNRERegistro1(registro));
                    } else if (tipo == 2) {
                        int i = registros1.size() - 1;
                        if (i >= 0) {
                            registros1.get(i)
                                    .addRegistro2(new LoteGuiasGNRERegistro2(registro));
                        }
                    } else if (tipo == 9) {
                        registro9 = new LoteGuiasGNRERegistro9(registro);
                    }
                });
    }

    public Optional<LoteGuiasGNRERegistro0> getRegistro0() {
        return Optional.ofNullable(registro0);
    }

    public List<LoteGuiasGNRERegistro1> getRegistros1() {
        return unmodifiableList(registros1);
    }

    public Optional<LoteGuiasGNRERegistro9> getRegistro9() {
        return Optional.ofNullable(registro9);
    }
}
