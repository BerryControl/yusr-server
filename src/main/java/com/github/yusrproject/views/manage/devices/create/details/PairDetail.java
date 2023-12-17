package com.github.yusrproject.views.manage.devices.create.details;

import com.github.yusrproject.views.manage.devices.create.State;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Getter;
import lombok.Setter;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class PairDetail extends Details {
    private final State state;
    @Getter
    @Setter
    private String pin = "";
    @Getter
    @Setter
    private String username = "";
    @Getter
    @Setter
    private String password = "";

    public PairDetail(State state) {
        super("Pair Device");
        this.state = state;
    }

    public PairDetail render() {
        this.removeAll();
        boolean enabled = state.getStep().value() >= State.Step.PAIR_DEVICE.value() && state.needsAuthentication();

        VerticalLayout layout = new VerticalLayout();
        Button startPairing = new Button("Start Pairing", LineAwesomeIcon.PLAY_SOLID.create(), this::onStartPairing);
        startPairing.setEnabled(state.getStep().value() >= State.Step.PAIR_DEVICE.value() && state.getStep() == State.Step.PAIR_DEVICE);

        layout.add(startPairing);
        if (state.getSelectedDriver() != null) {
            this.renderAuthenticationFields(layout);
        }

        Button pairButton = new Button("Pair", LineAwesomeIcon.LINK_SOLID.create(), this::onFinalizePairing);
        pairButton.setEnabled(state.getStep() == State.Step.PAIRING_STARTED);
        layout.add(pairButton);
        this.add(layout);

        this.setOpened(enabled);
        this.setEnabled(enabled);

        return this;
    }

    private void onFinalizePairing(ClickEvent<Button> buttonClickEvent) {
        state.finalizePairing(this.pin);
    }

    private void onStartPairing(ClickEvent<Button> buttonClickEvent) {
        state.startPairing();
    }

    private void renderAuthenticationFields(VerticalLayout layout) {
        switch (this.state.getSelectedDriver().authenticationMethod()) {
            case PIN -> this.renderPinAuthenticationFields(layout);
            case PASSWORD -> this.renderPasswordAuthenticationFields(layout);
            case USER_AND_PASSWORD -> this.renderUserAndPasswordAuthenticationFields(layout);
        }

    }

    private void renderPinAuthenticationFields(VerticalLayout layout) {
        TextField pinTextField = new TextField("PIN");

        pinTextField.setMinLength(4);
        pinTextField.setMaxLength(6);
        pinTextField.setRequiredIndicatorVisible(true);
        pinTextField.setAutocorrect(false);
        pinTextField.setValueChangeMode(ValueChangeMode.EAGER);
        pinTextField.setPattern("[0-9]*");
        pinTextField.addValueChangeListener(e -> {
            if (!pinTextField.isInvalid()) {
                pin = e.getValue();
            }
        });
        pinTextField.setEnabled(state.getStep() == State.Step.PAIRING_STARTED);

        layout.add(pinTextField);
    }

    private void renderPasswordAuthenticationFields(VerticalLayout layout) {
        
    }

    private void renderUserAndPasswordAuthenticationFields(VerticalLayout layout) {
        
        
    }
}
