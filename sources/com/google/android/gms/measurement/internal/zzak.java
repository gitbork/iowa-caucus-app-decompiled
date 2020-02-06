package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbx;
import com.google.android.gms.internal.measurement.zzcm;
import com.google.android.gms.internal.measurement.zzjh;
import com.google.android.gms.internal.measurement.zzkg;
import com.google.logging.type.LogSeverity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p006io.sentry.DefaultSentryClientFactory;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.0.1 */
public final class zzak {
    static zzr zza;
    public static zzdy<Long> zzaa;
    public static zzdy<Long> zzab;
    public static zzdy<Long> zzac;
    public static zzdy<Long> zzad;
    public static zzdy<Long> zzae;
    public static zzdy<Integer> zzaf;
    public static zzdy<Long> zzag;
    public static zzdy<Integer> zzah;
    public static zzdy<Integer> zzai;
    public static zzdy<Long> zzaj;
    public static zzdy<Boolean> zzak;
    public static zzdy<String> zzal;
    public static zzdy<Long> zzam;
    public static zzdy<Integer> zzan;
    public static zzdy<Double> zzao;
    public static zzdy<Integer> zzap;
    public static zzdy<Boolean> zzaq;
    public static zzdy<Boolean> zzar;
    public static zzdy<Boolean> zzas;
    public static zzdy<Boolean> zzat;
    public static zzdy<Boolean> zzau;
    public static zzdy<Boolean> zzav;
    public static zzdy<Boolean> zzaw;
    public static zzdy<Boolean> zzax;
    public static zzdy<Boolean> zzay;
    public static zzdy<Boolean> zzaz;
    public static zzdy<Boolean> zzb;
    public static zzdy<Boolean> zzba;
    public static zzdy<Boolean> zzbb;
    public static zzdy<Boolean> zzbc;
    public static zzdy<Boolean> zzbd;
    public static zzdy<Boolean> zzbe;
    public static zzdy<Boolean> zzbf;
    public static zzdy<Boolean> zzbg;
    public static zzdy<Boolean> zzbh;
    public static zzdy<Boolean> zzbi;
    public static zzdy<Boolean> zzbj;
    public static zzdy<Boolean> zzbk;
    public static zzdy<Boolean> zzbl;
    public static zzdy<Boolean> zzbm;
    public static zzdy<Boolean> zzbn;
    public static zzdy<Boolean> zzbo;
    public static zzdy<Boolean> zzbp;
    public static zzdy<Boolean> zzbq;
    public static zzdy<Boolean> zzbr;
    public static zzdy<Boolean> zzbs;
    public static zzdy<Boolean> zzbt;
    public static zzdy<Boolean> zzbu;
    public static zzdy<Boolean> zzbv;
    public static zzdy<Boolean> zzbw;
    public static zzdy<Boolean> zzbx;
    public static zzdy<Boolean> zzby;
    public static zzdy<Boolean> zzbz;
    public static zzdy<Boolean> zzc;
    public static zzdy<Boolean> zzca;
    public static zzdy<Boolean> zzcb;
    public static zzdy<Boolean> zzcc;
    public static zzdy<Boolean> zzcd;
    public static zzdy<Boolean> zzce;
    public static zzdy<Boolean> zzcf;
    public static zzdy<Boolean> zzcg;
    public static zzdy<Boolean> zzch;
    public static zzdy<Boolean> zzci;
    public static zzdy<Boolean> zzcj;
    public static zzdy<Boolean> zzck;
    /* access modifiers changed from: private */
    public static List<zzdy<?>> zzcl = Collections.synchronizedList(new ArrayList());
    private static Set<zzdy<?>> zzcm = Collections.synchronizedSet(new HashSet());
    private static volatile zzfn zzcn;
    @VisibleForTesting
    private static Boolean zzco;
    private static zzdy<Boolean> zzcp;
    private static zzdy<Boolean> zzcq;
    public static zzdy<String> zzd = zza("measurement.log_tag", "FA", "FA-SVC", zzaz.zza);
    public static zzdy<Long> zze;
    public static zzdy<Long> zzf;
    public static zzdy<Long> zzg;
    public static zzdy<String> zzh;
    public static zzdy<String> zzi;
    public static zzdy<Integer> zzj;
    public static zzdy<Integer> zzk;
    public static zzdy<Integer> zzl;
    public static zzdy<Integer> zzm;
    public static zzdy<Integer> zzn;
    public static zzdy<Integer> zzo;
    public static zzdy<Integer> zzp;
    public static zzdy<Integer> zzq;
    public static zzdy<Integer> zzr;
    public static zzdy<Integer> zzs;
    public static zzdy<String> zzt;
    public static zzdy<Long> zzu;
    public static zzdy<Long> zzv;
    public static zzdy<Long> zzw;
    public static zzdy<Long> zzx;
    public static zzdy<Long> zzy;
    public static zzdy<Long> zzz;

    public static Map<String, String> zza(Context context) {
        zzbx zza2 = zzbx.zza(context.getContentResolver(), zzcm.zza("com.google.android.gms.measurement"));
        return zza2 == null ? Collections.emptyMap() : zza2.zza();
    }

    static void zza(zzfn zzfn) {
        zzcn = zzfn;
    }

    @VisibleForTesting
    static void zza(Exception exc) {
        if (zzcn != null) {
            Context zzn2 = zzcn.zzn();
            if (zzco == null) {
                zzco = Boolean.valueOf(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(zzn2, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) == 0);
            }
            if (zzco.booleanValue()) {
                zzcn.zzr().zzf().zza("Got Exception on PhenotypeFlag.get on Play device", exc);
            }
        }
    }

    @VisibleForTesting
    private static <V> zzdy<V> zza(@Nullable String str, @Nullable V v, @Nullable V v2, @Nullable zzdz<V> zzdz) {
        zzdy zzdy = new zzdy(str, v, v2, zzdz);
        zzcl.add(zzdy);
        return zzdy;
    }

    static void zza(zzr zzr2) {
        zza = zzr2;
    }

    private static boolean zzcm() {
        if (zza != null) {
        }
        return false;
    }

    static final /* synthetic */ Long zzcf() {
        long j;
        if (zzcm()) {
            j = zzjh.zzq();
        } else {
            j = zzjh.zzc();
        }
        return Long.valueOf(j);
    }

    static final /* synthetic */ String zzci() {
        return zzcm() ? zzjh.zzs() : zzjh.zzd();
    }

    static {
        Boolean valueOf = Boolean.valueOf(false);
        zzb = zza("measurement.log_androidId_enabled", valueOf, valueOf, zzan.zza);
        zzc = zza("measurement.upload_dsid_enabled", valueOf, valueOf, zzam.zza);
        Long valueOf2 = Long.valueOf(10000);
        zze = zza("measurement.ad_id_cache_time", valueOf2, valueOf2, zzbi.zza);
        Long valueOf3 = Long.valueOf(86400000);
        zzf = zza("measurement.monitoring.sample_period_millis", valueOf3, valueOf3, zzbv.zza);
        Long valueOf4 = Long.valueOf(3600000);
        zzg = zza("measurement.config.cache_time", valueOf3, valueOf4, zzce.zza);
        zzdz zzdz = zzcr.zza;
        String str = UriUtil.HTTPS_SCHEME;
        zzh = zza("measurement.config.url_scheme", str, str, zzdz);
        String str2 = "app-measurement.com";
        zzi = zza("measurement.config.url_authority", str2, str2, zzda.zza);
        Integer valueOf5 = Integer.valueOf(100);
        zzj = zza("measurement.upload.max_bundles", valueOf5, valueOf5, zzdn.zza);
        Integer valueOf6 = Integer.valueOf(65536);
        zzk = zza("measurement.upload.max_batch_size", valueOf6, valueOf6, zzdx.zza);
        zzl = zza("measurement.upload.max_bundle_size", valueOf6, valueOf6, zzap.zza);
        Integer valueOf7 = Integer.valueOf(1000);
        zzm = zza("measurement.upload.max_events_per_bundle", valueOf7, valueOf7, zzao.zza);
        Integer valueOf8 = Integer.valueOf(100000);
        zzn = zza("measurement.upload.max_events_per_day", valueOf8, valueOf8, zzar.zza);
        zzo = zza("measurement.upload.max_error_events_per_day", valueOf7, valueOf7, zzaq.zza);
        Integer valueOf9 = Integer.valueOf(50000);
        zzp = zza("measurement.upload.max_public_events_per_day", valueOf9, valueOf9, zzat.zza);
        Integer valueOf10 = Integer.valueOf(500);
        zzq = zza("measurement.upload.max_conversions_per_day", valueOf10, valueOf10, zzas.zza);
        Integer valueOf11 = Integer.valueOf(10);
        zzr = zza("measurement.upload.max_realtime_events_per_day", valueOf11, valueOf11, zzav.zza);
        Integer valueOf12 = Integer.valueOf(100000);
        zzs = zza("measurement.store.max_stored_events_per_app", valueOf12, valueOf12, zzau.zza);
        String str3 = "https://app-measurement.com/a";
        zzt = zza("measurement.upload.url", str3, str3, zzax.zza);
        Long valueOf13 = Long.valueOf(43200000);
        zzu = zza("measurement.upload.backoff_period", valueOf13, valueOf13, zzaw.zza);
        zzv = zza("measurement.upload.window_interval", valueOf4, valueOf4, zzay.zza);
        zzw = zza("measurement.upload.interval", valueOf4, valueOf4, zzbb.zza);
        zzx = zza("measurement.upload.realtime_upload_interval", valueOf2, valueOf2, zzba.zza);
        Long valueOf14 = Long.valueOf(1000);
        zzy = zza("measurement.upload.debug_upload_interval", valueOf14, valueOf14, zzbd.zza);
        Long valueOf15 = Long.valueOf(500);
        zzz = zza("measurement.upload.minimum_delay", valueOf15, valueOf15, zzbc.zza);
        Long valueOf16 = Long.valueOf(DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT);
        zzaa = zza("measurement.alarm_manager.minimum_interval", valueOf16, valueOf16, zzbf.zza);
        zzab = zza("measurement.upload.stale_data_deletion_interval", valueOf3, valueOf3, zzbe.zza);
        Long valueOf17 = Long.valueOf(604800000);
        zzac = zza("measurement.upload.refresh_blacklisted_config_interval", valueOf17, valueOf17, zzbh.zza);
        Long valueOf18 = Long.valueOf(15000);
        zzad = zza("measurement.upload.initial_upload_delay_time", valueOf18, valueOf18, zzbg.zza);
        Long valueOf19 = Long.valueOf(1800000);
        zzae = zza("measurement.upload.retry_time", valueOf19, valueOf19, zzbj.zza);
        Integer valueOf20 = Integer.valueOf(6);
        zzaf = zza("measurement.upload.retry_count", valueOf20, valueOf20, zzbl.zza);
        Long valueOf21 = Long.valueOf(2419200000L);
        zzag = zza("measurement.upload.max_queue_time", valueOf21, valueOf21, zzbk.zza);
        Integer valueOf22 = Integer.valueOf(4);
        zzah = zza("measurement.lifetimevalue.max_currency_tracked", valueOf22, valueOf22, zzbn.zza);
        Integer valueOf23 = Integer.valueOf(LogSeverity.INFO_VALUE);
        zzai = zza("measurement.audience.filter_result_max_count", valueOf23, valueOf23, zzbm.zza);
        Long valueOf24 = Long.valueOf(5000);
        zzaj = zza("measurement.service_client.idle_disconnect_millis", valueOf24, valueOf24, zzbp.zza);
        zzak = zza("measurement.test.boolean_flag", valueOf, valueOf, zzbo.zza);
        String str4 = "---";
        zzal = zza("measurement.test.string_flag", str4, str4, zzbr.zza);
        Long valueOf25 = Long.valueOf(-1);
        zzam = zza("measurement.test.long_flag", valueOf25, valueOf25, zzbq.zza);
        Integer valueOf26 = Integer.valueOf(-2);
        zzan = zza("measurement.test.int_flag", valueOf26, valueOf26, zzbt.zza);
        Double valueOf27 = Double.valueOf(-3.0d);
        zzao = zza("measurement.test.double_flag", valueOf27, valueOf27, zzbs.zza);
        Integer valueOf28 = Integer.valueOf(50);
        zzap = zza("measurement.experiment.max_ids", valueOf28, valueOf28, zzbu.zza);
        zzaq = zza("measurement.validation.internal_limits_internal_event_params", valueOf, valueOf, zzbx.zza);
        Boolean valueOf29 = Boolean.valueOf(true);
        zzar = zza("measurement.audience.dynamic_filters", valueOf29, valueOf29, zzbw.zza);
        zzas = zza("measurement.reset_analytics.persist_time", valueOf, valueOf, zzbz.zza);
        zzat = zza("measurement.validation.value_and_currency_params", valueOf29, valueOf29, zzby.zza);
        zzau = zza("measurement.sampling.time_zone_offset_enabled", valueOf, valueOf, zzcb.zza);
        zzav = zza("measurement.referrer.enable_logging_install_referrer_cmp_from_apk", valueOf, valueOf, zzca.zza);
        zzaw = zza("measurement.client.sessions.session_id_enabled", valueOf, valueOf, zzcd.zza);
        zzax = zza("measurement.service.sessions.session_number_enabled", valueOf29, valueOf29, zzcc.zza);
        zzay = zza("measurement.client.sessions.immediate_start_enabled_foreground", valueOf, valueOf, zzcf.zza);
        zzaz = zza("measurement.client.sessions.background_sessions_enabled", valueOf, valueOf, zzch.zza);
        zzba = zza("measurement.client.sessions.remove_expired_session_properties_enabled", valueOf, valueOf, zzcg.zza);
        zzbb = zza("measurement.service.sessions.session_number_backfill_enabled", valueOf29, valueOf29, zzcj.zza);
        zzbc = zza("measurement.service.sessions.remove_disabled_session_number", valueOf29, valueOf29, zzci.zza);
        zzbd = zza("measurement.collection.firebase_global_collection_flag_enabled", valueOf29, valueOf29, zzcl.zza);
        zzbe = zza("measurement.collection.efficient_engagement_reporting_enabled", valueOf, valueOf, zzck.zza);
        zzbf = zza("measurement.collection.redundant_engagement_removal_enabled", valueOf, valueOf, zzcn.zza);
        zzbg = zza("measurement.personalized_ads_signals_collection_enabled", valueOf29, valueOf29, zzcm.zza);
        zzbh = zza("measurement.personalized_ads_property_translation_enabled", valueOf29, valueOf29, zzcp.zza);
        zzbi = zza("measurement.collection.init_params_control_enabled", valueOf29, valueOf29, zzco.zza);
        zzbj = zza("measurement.upload.disable_is_uploader", valueOf29, valueOf29, zzcq.zza);
        zzbk = zza("measurement.experiment.enable_experiment_reporting", valueOf29, valueOf29, zzct.zza);
        zzbl = zza("measurement.collection.log_event_and_bundle_v2", valueOf29, valueOf29, zzcs.zza);
        zzbm = zza("measurement.collection.null_empty_event_name_fix", valueOf29, valueOf29, zzcv.zza);
        zzbn = zza("measurement.audience.sequence_filters", valueOf29, valueOf29, zzcu.zza);
        zzbo = zza("measurement.quality.checksum", valueOf, valueOf, null);
        zzbp = zza("measurement.module.collection.conditionally_omit_admob_app_id", valueOf29, valueOf29, zzcx.zza);
        zzbq = zza("measurement.sdk.dynamite.use_dynamite2", valueOf, valueOf, zzcw.zza);
        zzbr = zza("measurement.sdk.dynamite.allow_remote_dynamite", valueOf, valueOf, zzcz.zza);
        zzbs = zza("measurement.sdk.collection.validate_param_names_alphabetical", valueOf, valueOf, zzcy.zza);
        zzbt = zza("measurement.collection.event_safelist", valueOf, valueOf, zzdb.zza);
        zzbu = zza("measurement.service.audience.scoped_filters_v27", valueOf, valueOf, zzdd.zza);
        zzbv = zza("measurement.service.audience.session_scoped_event_aggregates", valueOf, valueOf, zzdc.zza);
        zzbw = zza("measurement.service.audience.session_scoped_user_engagement", valueOf, valueOf, zzdf.zza);
        zzbx = zza("measurement.service.audience.remove_disabled_session_scoped_user_engagement", valueOf, valueOf, zzde.zza);
        zzby = zza("measurement.service.audience.use_bundle_timestamp_for_property_filters", valueOf, valueOf, zzdh.zza);
        zzbz = zza("measurement.service.audience.not_prepend_timestamps_for_sequence_session_scoped_filters", valueOf, valueOf, zzdg.zza);
        zzca = zza("measurement.sdk.collection.retrieve_deeplink_from_bow", valueOf, valueOf, zzdj.zza);
        zzcb = zza("measurement.app_launch.event_ordering_fix", valueOf, valueOf, zzdi.zza);
        zzcc = zza("measurement.sdk.collection.last_deep_link_referrer", valueOf, valueOf, zzdl.zza);
        zzcp = zza("measurement.sdk.collection.last_deep_link_referrer_campaign", valueOf, valueOf, zzdk.zza);
        zzcd = zza("measurement.sdk.collection.last_gclid_from_referrer", valueOf, valueOf, zzdm.zza);
        zzce = zza("measurement.upload.file_lock_state_check", valueOf, valueOf, zzdp.zza);
        zzcf = zza("measurement.sampling.calculate_bundle_timestamp_before_sampling", valueOf29, valueOf29, zzdo.zza);
        zzcq = zza("measurement.ga.ga_app_id", valueOf, Boolean.valueOf(zzkg.zzb()), zzdr.zza);
        zzcg = zza("measurement.lifecycle.app_backgrounded_tracking", valueOf, valueOf, zzdq.zza);
        zzch = zza("measurement.lifecycle.app_in_background_parameter", valueOf, valueOf, zzdt.zza);
        zzci = zza("measurement.integration.disable_firebase_instance_id", valueOf, valueOf, zzds.zza);
        zzcj = zza("measurement.lifecycle.app_backgrounded_engagement", valueOf, valueOf, zzdv.zza);
        zzck = zza("measurement.service.fix_gmp_version", valueOf, valueOf, zzdu.zza);
    }
}
