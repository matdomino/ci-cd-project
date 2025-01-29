Należy uruchomić w katalogu `kubernetes` za pomocą komendy:

linux:
```
helm install at-app . \
  --set postgres.username="{TUTAJ PODAC UZYTKOWNIKA}" \
  --set postgres.password="{TUTAJ PODAC HASLO}" \
  --set postgres.database="{TUTAJ PODAC BAZE DANYCH}"
```

windows:
```
helm install at-app . --set postgres.username="{TUTAJ PODAC UZYTKOWNIKA}" --set postgres.password="{TUTAJ PODAC HASLO}" --set postgres.database="{TUTAJ PODAC BAZE DANYCH}"
```