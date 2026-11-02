package hu.mik.pte.prog5.zh1vaadin.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.security.AuthenticationContext;
import hu.mik.pte.prog5.zh1vaadin.service.PetService;
import hu.mik.pte.prog5.zh1vaadin.ui.view.ListView;
import hu.mik.pte.prog5.zh1vaadin.ui.view.RegisterView;
import org.jetbrains.kotlin.it.unimi.dsi.fastutil.bytes.B;
import org.jetbrains.kotlin.it.unimi.dsi.fastutil.ints.H;


import java.util.Locale;
@AnonymousAllowed
public class MainLayout extends VerticalLayout implements RouterLayout {
    private final Div content = new Div();
    private final AuthenticationContext authenticationContext;

    public MainLayout(AuthenticationContext authenticationContext)
    {
        this.authenticationContext = authenticationContext;

        this.setSizeFull();
        this.setMaxWidth("900px");
        this.getStyle().set("margin","0 auto");

        this.content.setWidthFull();
        this.add(this.createHeader(),this.content);
        //this.setFlexGrow(1,this.content);
    }

    private Header createHeader()
    {
        HorizontalLayout bar = new HorizontalLayout();
        bar.setWidthFull();
        bar.setAlignItems(Alignment.CENTER);
        if(this.authenticationContext.isAuthenticated())
        {
            String username = this.authenticationContext.getPrincipalName().orElse("");
            Button logout = new Button(this.getTranslation("header.menu.logout"), e -> this.authenticationContext.logout());
            Button list = new Button(this.getTranslation("header.menu.pet.list"), e -> UI.getCurrent().navigate(ListView.class));
            bar.add(new Span(this.getTranslation("header.username") + ": " + username),list);
            if(this.authenticationContext.hasRole("REGISTER"))
            {
                Button reg = new Button(this.getTranslation("header.menu.pet.register"), e -> UI.getCurrent().navigate(RegisterView.class));
                bar.add(reg);
            }
            bar.add(logout);
        }

        Header header = new Header(bar);
        header.setWidthFull();
        return header;
    }

}
