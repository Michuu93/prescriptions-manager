import {HttpClient} from "@angular/common/http";
import {EventEmitter, Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {PatientsPage} from "./model/patients-page.model";
import {Patient} from "./model/patient.model";

@Injectable({providedIn: 'root'})
export class PatientsService {
    patientEdited = new EventEmitter<Patient>();
    editPatient = new EventEmitter();
    patientSaved = new EventEmitter();

    constructor(private http: HttpClient) {
    }

    getPatientsPage(page: number, size: number): Observable<PatientsPage> {
        return this.http.get<PatientsPage>(`/api/patients/?page=${page}&size=${size}`);
    }

    savePatient(patient: Patient): Observable<Patient> {
        return this.http.post<Patient>('/api/patients/', patient);
    }
}