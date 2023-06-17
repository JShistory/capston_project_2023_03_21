package com.example.base.controller;

import com.example.base.domain.Patient;
import com.example.base.domain.SessionUser;
import com.example.base.domain.WearableEquipment;
import com.example.base.repository.PatientRepository;
import com.example.base.repository.PatientSearch;
import com.example.base.repository.WearableEquipmentRepository;
import com.example.base.service.PatientService;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final WearableEquipmentRepository wearableEquipmentRepository;


    @GetMapping("patients/new")
    public String createForm(Model model) {
        model.addAttribute("form", new PatientForm());
        return "patients/createPatientForm";
    }

    @PostMapping("patients/new")
    public String create(@Valid @ModelAttribute("form") PatientForm patientForm, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "patients/createPatientForm";
        }

        Patient patient = new Patient();
        patient.setPatientName(patientForm.getName());
        patient.setBirthday(patientForm.getBirthday());
        patient.setGender(patientForm.getGender());
        patient.setCorrectionTime(patientForm.getCorrectionTime());
        if (patientForm.getWearingTime() == null) {
            patient.setWearingTime(0L);
        } else {
            patient.setWearingTime(patientForm.getWearingTime());
        }

        patient.setCorrectionDay(patientForm.getCorrectionDay());

        if (patientForm.getWearingDay() == null) {
            patient.setWearingDay(0L);
        } else {
            patient.setWearingDay(patientForm.getWearingDay());
        }
        patient.setGuardianPhoneNumber(patientForm.getGuardianPhoneNumber());
        patient.setTimeToWear(patientForm.getTimeToWear());

        patientService.createPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients")
    public String patientsList(@ModelAttribute("patientSearch") PatientSearch patientSearch, Model model,
                               HttpSession httpSession) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("userRole", user.getRole());

        }

        List<Patient> patients = patientService.findPatients(patientSearch);

        model.addAttribute("patients", patients);
        List<Patient> patients1 = patientRepository.findAll();
        if (!patients1.isEmpty()) {
            for (Patient patient : patients1) {
                detailForm(patient.getId(), model);
            }
        }
        return "patients/patientList";
    }


    @GetMapping("patients/{patientId}/delete")
    public String deletePatients(@PathVariable("patientId") Long patientId) {
        patientService.delete(patientId);
        return "redirect:/patients";
    }

    @GetMapping("patients/{patientId}/detail")
    public String detailForm(@PathVariable("patientId") Long patientId, Model model) {
        HashMap<Integer, Integer> timeMap = new HashMap<>();
        List<WearableEquipment> all = wearableEquipmentRepository.findAll();
        Patient patient = patientService.findOne(patientId);

        //착용한 시간
        double wearableTime = 0;
        //착용한 날
        int wearableDay = 0;
        //착용 시간 계산
        double timeResult = 0;
        //착용 한 날 계산
        double dayResult = 0;
        //착용 해야 하는 날
        double correctDay = 0;
        //착용 해야 하는 시간
        double correctTime = 0;

        List<String> startList = new ArrayList<>();
        List<String> endList = new ArrayList<>();

        model.addAttribute("startList", startList);
        model.addAttribute("endList", endList);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        if (!all.isEmpty()) {

            LocalDateTime startTime = LocalDateTime.parse(all.get(0).getStartTime(), format);
            LocalDateTime endTime = LocalDateTime.parse(all.get(all.size() - 1).getEndTime(), format);
            LocalDateTime now = LocalDateTime.now();

            correctDay = ChronoUnit.DAYS.between(startTime, now) + 1;

            for (WearableEquipment wearableEquipment : all) {

                if (wearableEquipment.getPatient().getId() == patientId) {
//                Duration duration = Duration.between(wearableEquipment.getStartTime(), wearableEquipment.getEndTime());
                    LocalDateTime start = LocalDateTime.parse(wearableEquipment.getStartTime(), format);
                    if (start.isAfter(now)) {
                        continue;
                    }
                    LocalDateTime end = LocalDateTime.parse(wearableEquipment.getEndTime(), format);
                    startList.add(wearableEquipment.getStartTime());
                    endList.add(wearableEquipment.getEndTime());
                    Duration duration = Duration.between(start, end);

                    timeMap.put(start.getDayOfYear(),
                            (int) (timeMap.getOrDefault(start.getDayOfYear(), 0) + duration.getSeconds() / 60 / 60));
                    //시간을 기준으로
                    wearableTime += duration.getSeconds() / 60 / 60;
                }

            }

            //교정시간에 80%이상 착용했다면 1일 추가
            for (Integer data : timeMap.keySet()) {
                if (timeMap.get(data) >= patient.getTimeToWear() * 0.8) {
                    wearableDay += 1;
                }
            }

            //만약에 time == 0이면, 즉 환자가 착용한 장비가 없으면
            if (wearableTime == 0) {
                model.addAttribute("correctTime", 0);
                model.addAttribute("correctDay", 0);
            } else {
                model.addAttribute("correctTime", (long) correctDay * patient.getTimeToWear());
                model.addAttribute("correctDay", (long) correctDay);

            }
            correctTime = patient.getTimeToWear() * correctDay;

            timeResult = Math.round((wearableTime / correctTime) * 100);
            dayResult = Math.round(((double) wearableDay / patient.getCorrectionDay()) * 100);

            model.addAttribute("form", patient);
            model.addAttribute("wearableDay", wearableDay);
            model.addAttribute("wearableTime", (long) wearableTime);
            model.addAttribute("timeResult", timeResult);
            model.addAttribute("dayResult", dayResult);

        }

        patientService.updatePatient(patientId, patient.getPatientName(), patient.getBirthday(),
                patient.getGender(), patient.getGuardianPhoneNumber(), patient.getCorrectionTime(), (long) wearableTime,
                patient.getCorrectionDay(), (long) wearableDay, patient.getTimeToWear());
        return "patients/detailPatient";
    }


    @GetMapping("patients/{patientId}/edit")
    public String updatePatientForm(@PathVariable("patientId") Long patientId, Model model) {

        Patient patient = patientService.findOne(patientId);

        PatientForm form = new PatientForm();

        form.setName(patient.getPatientName());
        form.setBirthday(patient.getBirthday());
        form.setGuardianPhoneNumber(patient.getGuardianPhoneNumber());
        form.setCorrectionTime(patient.getCorrectionTime());
        form.setWearingTime(patient.getWearingTime());
        form.setCorrectionDay(patient.getCorrectionDay());
        form.setWearingDay(patient.getWearingDay());
        form.setGender(patient.getGender());
        form.setTimeToWear(patient.getTimeToWear());

        model.addAttribute("form", form);
        return "patients/updatePatientForm";
    }

    @PostMapping("patients/{patientId}/edit")
    public String updatePatient(@Valid @PathVariable Long patientId, @ModelAttribute("form") PatientForm form,
                                BindingResult result) {

        if (result.hasErrors()) {
            return "patients/updatePatientForm";
        }
        patientService.updatePatient(patientId, form.getName(), form.getBirthday(), form.getGender(),
                form.getGuardianPhoneNumber(),
                form.getCorrectionTime(), form.getWearingTime(), form.getCorrectionDay(), form.getWearingDay(),
                form.getTimeToWear());
        return "redirect:/patients";
    }

    @GetMapping("patients/time")
    public String timeList(Model model) {
        List<WearableEquipment> all = wearableEquipmentRepository.findAll();

        List<String> startList = new ArrayList<>();
        List<String> endList = new ArrayList<>();
        for (WearableEquipment wearableEquipment : all) {
            if (wearableEquipment.getPatient().getId() == 1L) {
//                Duration duration = Duration.between(wearableEquipment.getStartTime(), wearableEquipment.getEndTime());
                startList.add(wearableEquipment.getStartTime());
                endList.add(wearableEquipment.getEndTime());
            }
        }

        model.addAttribute("startList", startList);
        model.addAttribute("endList", endList);
        return "members/time";
    }
}
