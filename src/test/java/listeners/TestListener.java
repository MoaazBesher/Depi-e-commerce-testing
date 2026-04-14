package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Professional TestNG Listener that:
 *  1. Produces a formatted console log with timestamps, status, and durations.
 *  2. Generates a Dark-themed ExtentReports HTML report under target/extent-reports/.
 */
public class TestListener implements ITestListener, ISuiteListener {

    // ── ExtentReports ──────────────────────────────────────────────────────
    private ExtentReports extent;
    private final Map<String, ExtentTest> testStore = new ConcurrentHashMap<>();
    private final Map<String, Long> timeStore  = new ConcurrentHashMap<>();

    // ── Counters ───────────────────────────────────────────────────────────
    private final AtomicInteger total   = new AtomicInteger();
    private final AtomicInteger passed  = new AtomicInteger();
    private final AtomicInteger failed  = new AtomicInteger();
    private final AtomicInteger skipped = new AtomicInteger();
    private long suiteStart;

    // ── Formatting constants ───────────────────────────────────────────────
    private static final DateTimeFormatter CLOCK = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final int BOX_INNER = 70; // box content width
    private static final String BAR    = "=".repeat(BOX_INNER + 2);
    private static final String MID    = "-".repeat(BOX_INNER + 2);

    // ══════════════════════════════════════════════════════════════════════
    //  Suite lifecycle
    // ══════════════════════════════════════════════════════════════════════

    @Override
    public void onStart(ISuite suite) {
        suiteStart = System.currentTimeMillis();
        initExtent();
        printBanner(suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
        printSummary();
    }

    // ══════════════════════════════════════════════════════════════════════
    //  Test-method lifecycle
    // ══════════════════════════════════════════════════════════════════════

    @Override
    public void onTestStart(ITestResult r) {
        String id = id(r);
        timeStore.put(id, System.currentTimeMillis());

        ExtentTest t = extent.createTest(
                r.getTestClass().getRealClass().getSimpleName() + " > " + r.getMethod().getMethodName(),
                r.getMethod().getDescription()
        );
        testStore.put(id, t);

        System.out.printf("  [%s]  >> RUNNING  %-65s%n", now(), label(r));
    }

    @Override
    public void onTestSuccess(ITestResult r) {
        String id = id(r);
        long   ms = elapsed(id);
        passed.incrementAndGet();
        total.incrementAndGet();

        testStore.get(id).pass("Passed in " + fmt(ms));
        System.out.printf("  [%s]  PASSED     %-65s  [%s]%n", now(), label(r), fmt(ms));
    }

    @Override
    public void onTestFailure(ITestResult r) {
        String id = id(r);
        long   ms = elapsed(id);
        failed.incrementAndGet();
        total.incrementAndGet();

        Throwable ex = r.getThrowable();
        testStore.get(id).fail(ex);

        System.out.printf("  [%s]  FAILED     %-65s  [%s]%n", now(), label(r), fmt(ms));
        if (ex != null && ex.getMessage() != null) {
            String msg = ex.getMessage().replaceAll("\\s+", " ").trim();
            if (msg.length() > 100) msg = msg.substring(0, 100) + "...";
            System.out.printf("                   |-- %s%n", msg);
        }
    }

    @Override
    public void onTestSkipped(ITestResult r) {
        String id = id(r);
        skipped.incrementAndGet();
        total.incrementAndGet();
        testStore.computeIfAbsent(id, k -> extent.createTest(label(r))).skip("Skipped");
        System.out.printf("  [%s]  SKIPPED    %s%n", now(), label(r));
    }

    // ══════════════════════════════════════════════════════════════════════
    //  ExtentReports initialisation
    // ══════════════════════════════════════════════════════════════════════

    private void initExtent() {
        String ts   = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String path = "target/extent-reports/Report_" + ts + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("DEPI – MarketSpace Test Report");
        spark.config().setReportName("Automation Test Results");
        spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Application", "MarketSpace Demo");
        extent.setSystemInfo("Base URL",    "https://demo.getmarketspace.com");
        extent.setSystemInfo("Browser",     "Google Chrome");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Team",        "DEPI");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  Console output helpers
    // ══════════════════════════════════════════════════════════════════════

    private void printBanner(String name) {
        System.out.println();
        System.out.println("  +" + BAR + "+");
        boxRow("Suite   : " + name);
        boxRow("URL     : https://demo.getmarketspace.com");
        boxRow("Browser : Google Chrome");
        boxRow("Started : " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss")));
        System.out.println("  +" + BAR + "+");
        System.out.println();
        System.out.println("  " + "-".repeat(98));
        System.out.printf("  %-12s  %-12s  %-65s  %s%n", "  TIME", " STATUS", "  TEST NAME", " DURATION");
        System.out.println("  " + "-".repeat(98));
    }

    private void printSummary() {
        long   ms  = System.currentTimeMillis() - suiteStart;
        int    t   = total.get(), p = passed.get(), f = failed.get(), s = skipped.get();
        double rt  = t > 0 ? (p * 100.0 / t) : 0.0;
        String dur = ms >= 60_000
                ? (ms / 60000) + "m " + ((ms % 60000) / 1000) + "s"
                : (ms / 1000) + "s";

        System.out.println();
        System.out.println("  +" + BAR + "+");
        boxRow("TEST SUITE SUMMARY");
        System.out.println("  +" + MID + "+");
        sRow("Total Tests", String.valueOf(t));
        sRow("PASSED",      String.valueOf(p));
        sRow("FAILED",      String.valueOf(f));
        sRow("SKIPPED",     String.valueOf(s));
        sRow("Duration",    dur);
        sRow("Pass Rate",   String.format("%.1f%%", rt));
        System.out.println("  +" + MID + "+");

        String verdict = (f == 0 && t > 0)
                ? "ALL TESTS PASSED!"
                : (f > 0)
                ? f + " TEST(S) FAILED -- Review Required"
                : "No tests were executed.";
        boxRow(verdict);
        System.out.println("  +" + MID + "+");
        boxRow("HTML Report --> target/extent-reports/");
        System.out.println("  +" + BAR + "+");
        System.out.println();
    }

    /** Print a single-column box row */
    private void boxRow(String content) {
        // "  |  " = 5 chars, content, "  |" = 3 chars => total = 5 + BOX_INNER + 1
        System.out.printf("  |  %-" + BOX_INNER + "s  |%n", content);
    }

    /** Print a two-column summary row */
    private void sRow(String label, String value) {
        System.out.printf("  |  %-14s |  %-53s  |%n", label, value);
    }

    // ══════════════════════════════════════════════════════════════════════
    //  Utility
    // ══════════════════════════════════════════════════════════════════════

    private String id(ITestResult r) {
        return r.getTestClass().getRealClass().getName() + "#" + r.getMethod().getMethodName();
    }

    private String label(ITestResult r) {
        return r.getTestClass().getRealClass().getSimpleName()
                + "." + r.getMethod().getMethodName();
    }

    private long elapsed(String id) {
        Long t = timeStore.get(id);
        return t != null ? System.currentTimeMillis() - t : 0L;
    }

    private String fmt(long ms) {
        if (ms >= 60_000) return (ms / 60000) + "m " + ((ms % 60000) / 1000) + "s";
        if (ms >= 1_000)  return String.format("%.1fs", ms / 1000.0);
        return ms + "ms";
    }

    private String now() {
        return LocalDateTime.now().format(CLOCK);
    }
}
