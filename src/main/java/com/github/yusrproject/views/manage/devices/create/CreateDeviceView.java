package com.github.yusrproject.views.manage.devices.create;

import com.github.yusrproject.persistence.repository.PairedDevicesRepository;
import com.github.yusrproject.registry.DeviceRegistry;
import com.github.yusrproject.views.MainLayout;
import com.github.yusrproject.views.manage.devices.create.details.DeviceSelectionDetail;
import com.github.yusrproject.views.manage.devices.create.details.DriverSelectionDetail;
import com.github.yusrproject.views.manage.devices.create.details.PairDetail;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle(CreateDeviceView.TITLE)
@Route(value = CreateDeviceView.ROUTE, layout = MainLayout.class)
public class CreateDeviceView extends VerticalLayout {
    public static final String TITLE = "Create Device";
    public static final String ROUTE = "create_device";

    private final DeviceRegistry deviceRegistry;
    private final PairedDevicesRepository pairedDevicesRepository;

    private final State state;

    private final DriverSelectionDetail driverSelectionDetail;
    private final DeviceSelectionDetail deviceSelectionDetail;
    private final PairDetail pairDetail;

    public CreateDeviceView(@Autowired DeviceRegistry deviceRegistry, @Autowired PairedDevicesRepository pairedDevicesRepository) {
        this.pairedDevicesRepository = pairedDevicesRepository;
        this.state = new State(this, this.pairedDevicesRepository);
        this.deviceRegistry = deviceRegistry;

        this.driverSelectionDetail = new DriverSelectionDetail(this.state, this.deviceRegistry);
        this.deviceSelectionDetail = new DeviceSelectionDetail(this.state, this.deviceRegistry);
        this.pairDetail = new PairDetail(this.state);

        renderView();
    }

    public void renderView() {
        this.removeAll();

        this.add(this.driverSelectionDetail.render());
        this.add(this.deviceSelectionDetail.render());
        this.add(this.pairDetail.render());
    }
}
