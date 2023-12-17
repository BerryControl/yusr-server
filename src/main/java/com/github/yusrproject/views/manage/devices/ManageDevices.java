package com.github.yusrproject.views.manage.devices;

import com.github.yusrproject.views.MainLayout;
import com.github.yusrproject.views.manage.devices.create.CreateDeviceView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class ManageDevices  extends SideNavItem {
    public static final String TITLE = "Manage Devices";

    public ManageDevices() {
        super(TITLE, ManageDevicesView.class, LineAwesomeIcon.TV_SOLID.create());
    }

    @PageTitle(TITLE)
    @Route(value = ManageDevicesView.ROUTE, layout = MainLayout.class)
    public static class ManageDevicesView extends VerticalLayout {
        public static final String ROUTE = "manage_devices";

        public ManageDevicesView() {
            Button addDeviceBtn = new Button(
                    "Add Device", LineAwesomeIcon.PLUS_CIRCLE_SOLID.create(), this::onAddDevice);
            addDeviceBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            addDeviceBtn.setWidth("100%");
            add(addDeviceBtn);

            add(new Text("Device management view goes here."));
        }

        private void onAddDevice(ClickEvent<Button> buttonClickEvent) {
            getUI().ifPresent(ui -> ui.navigate(CreateDeviceView.ROUTE));
        }
    }
}
