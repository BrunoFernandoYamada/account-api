package br.com.byamada.accountapi.domain.enums;

public enum OperationTypeEnum {
    COMPRA_A_VISTA("COMPRA A VISTA"),
    COMPRA_PARCELADA("COMPRA PARCELADA"),
    SAQUE("SAQUE"),
    PAGAMENTO("PAGAMENTO");

    private String name;

    OperationTypeEnum(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }


}
