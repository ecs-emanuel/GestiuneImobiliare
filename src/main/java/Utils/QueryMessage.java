package Utils;

import java.awt.*;

public enum QueryMessage
{
    QUERY_SUCCESS("Sarcina indeplinita cu succes", CustomColor.GREEN_STRONG),
    RESULT_EMPTY("Fara rezultat", CustomColor.RED_STRONG),
    DATABASE_ERROR("Ceva nu a mers bine", CustomColor.RED_STRONG),
    DATABASE_OFFLINE("Nu s-a putut efectua conexiunea", CustomColor.RED_STRONG),
    ACCOUNT_WRONG("Credentiale gresite", CustomColor.RED_STRONG),
    ACCOUNT_CURRUPTED("Credentiale corupte", CustomColor.RED_STRONG),
    INSERT_SUCCESS("Datele au fost introduse cu succes", CustomColor.GREEN_STRONG),
    FORM_INCOMPLETE("Formularul nu este complet", CustomColor.RED_STRONG);

    String message;
    CustomColor color;

    QueryMessage(String message, CustomColor color)
    {
        this.message = message;
        this.color = color;
    }

    public String getMessage()
    {
        return this.message;
    }

    public CustomColor getCustomColor()
    {
        return this.color;
    }

    public Color getColor()
    {
        return this.color.getColor();
    }
}
