/*
 * Copyright (c) 2002-5 Gregor Heinrich, Arbylon. All rights reserved. No
 * redistribution prior to release, in source or binary forms, without written
 * consent of the author. A release will be considered after functionality has
 * been thoroughly tested and after licensing is clearly defined. All
 * contributors are required to comply with this and keep the code confidential.
 */
/*
 * Created on Jun 1, 2004 To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.knowceans.util;

import java.util.Arrays;

/**
 * Diverse sampling methods, including beta, gamma, multinomial, and Dirichlet
 * distributions as well as Dirichlet processes, using Sethurahman's
 * stick-breaking construction and Chinese restaurant process. The random
 * generator used is a Mersenne Twister (Cokus), which is the only dependency.
 * <p>
 * FIXME: markov condition in random generator, see random string?
 * 
 * @author heinrich (partly adapted from Yee Whye Teh's npbayes Matlab / C code)
 */
public class Samplers {

    // TODO: hook to random number generator instance

    public static void main(String[] args) {
        // double x = 0.4;
        // double y = 0.8;
        // double[] probs = {0.5, 0.5};
        // double[][] means = { {x, 1 - x}, {y, 1 - y}};
        // double[] precisions = {15, 15};
        // double[][] xx = new double[200000][];
        // int[] comps = new int[xx.length];
        // xx = randDmm(xx.length, probs, means, precisions, comps);
        // double[] result = Vectors.chooseColumn(xx, 0);
        // Histogram.hist(System.out, result, 100);
        // Histogram.hist(System.out, comps, 10);

        double a = 0.4;
        double k = 0;
        int I = 100;
        for (int i = 0; i < I; i++) {
            int m = randAntoniak(a, 14);
            k += m;
            System.out.println(m);
        }
        System.out.println(k / I);
    }

    protected static boolean haveNextNextGaussian = false;

    protected static double nextNextGaussian;

    public static double lastRand;

    protected static double drand48() {
        return Cokus.randDouble();
    }

    /**
     * uses same approach as java.util.Random()
     * 
     * @param mu
     * @param sigma
     * @return
     */
    public static double randNorm(double mu, double sigma) {

        // Random r = new Random();
        // return r.nextGaussian() * sigma + mu;
        if (haveNextNextGaussian) {
            haveNextNextGaussian = false;
            return nextNextGaussian * sigma + mu;
        } else {
            double v1, v2, s;
            do {
                v1 = 2 * drand48() - 1; // between -1 and 1
                v2 = 2 * drand48() - 1; // between -1 and 1
                s = v1 * v1 + v2 * v2;
            } while (s >= 1 || s == 0);
            double multiplier = Math.sqrt(-2 * Math.log(s) / s);
            nextNextGaussian = v2 * multiplier;
            haveNextNextGaussian = true;
            return v1 * multiplier * sigma + mu;
        }
    }

    /**
     * GMM sampling
     * 
     * @param probs
     *            mixture responsibilities
     * @param mean
     *            mean vector
     * @param sigma
     *            stddev vector
     * @return
     */
    public static double randGmm(double[] probs, double[] mean, double[] sigma) {
        return randGmm(1, probs, mean, sigma, null)[0];
    }

    /**
     * GMM sampling
     * 
     * @param probs
     *            mixture responsibilities
     * @param mean
     *            mean vector
     * @param sigma
     *            stddev vector
     * @param component
     *            [out] componentn[0] is filled with the sampled component index
     * @return
     */
    public static double randGmm(double[] probs, double[] mean, double[] sigma,
            int[] component) {
        return randGmm(1, probs, mean, sigma, null)[0];
    }

    /**
     * GMM sampling
     * 
     * @param n
     *            number of samples to take (this saves the calculation of the
     *            cumulative probabilities for successive trials)
     * @param probs
     *            mixture responsibilities
     * @param mean
     *            mean vector
     * @param sigma
     *            stddev vector
     * @return
     */
    public static double[] randGmm(int n, double[] probs, double[] mean,
            double[] sigma) {
        return randGmm(n, probs, mean, sigma, null);
    }

    /**
     * GMM sampling
     * 
     * @param n
     *            number of samples to take (this saves the calculation of the
     *            cumulative probabilities for successive trials)
     * @param probs
     *            mixture responsibilities
     * @param mean
     *            mean vector
     * @param sigma
     *            stddev vector
     * @param components
     *            [out] n-vector is filled with the sampled component indices
     *            (ignored if null)
     * @return
     */
    public static double[] randGmm(int n, double[] probs, double[] mean,
            double[] sigma, int[] components) {
        double[] x = new double[n];
        int k = probs.length;
        // init random number generator

        // multinomial cdf for probs
        double[] cumprobs = new double[k];
        cumprobs[0] = probs[0];
        for (int i = 1; i < k; i++) {
            cumprobs[i] = cumprobs[i - 1] + probs[i];
        }

        for (int i = 0; i < n; i++) {

            // multinomial index sampling
            double s = drand48();
            int c = 0;
            for (c = 0; c < k; c++) {
                if (s < cumprobs[c])
                    break;
            }
            if (components != null) {
                components[i] = c;
            }
            // normal component sampling
            x[i] = randNorm(mean[c], sigma[c]);
        }
        return x;
    }

    /**
     * DMM sampling
     * 
     * @param probs
     *            mixture responsibilities
     * @param mean
     *            mean vector of vectors
     * @param precision
     *            precision vector
     * @return
     */
    public static double[] randDmm(double[] probs, double[][] mean,
            double[] precision) {
        return randDmm(1, probs, mean, precision, null)[0];
    }

    /**
     * DMM sampling
     * 
     * @param probs
     *            mixture responsibilities
     * @param mean
     *            mean vector of vectors
     * @param precision
     *            precision vector
     * @param component
     *            [out] sampled component of the mixture (or ignored if null)
     * @return
     */
    public static double[] randDmm(double[] probs, double[][] mean,
            double[] precision, int[] component) {
        return randDmm(1, probs, mean, precision, component)[0];
    }

    /**
     * DMM sampling
     * 
     * @param probs
     *            mixture responsibilities
     * @param means
     *            mean vector of vectors
     * @param precisions
     *            precision vector
     * @return
     */
    public static double[][] randDmm(int n, double[] probs, double[][] means,
            double[] precisions) {
        return randDmm(n, probs, means, precisions, null);
    }

    /**
     * DMM sampling
     * 
     * @param n
     *            number of trials
     * @param probs
     *            mixture responsibilities
     * @param means
     *            mean vector of vectors
     * @param precisions
     *            precision vector
     * @param components
     *            n-vector is filled with the sampled component indices (ignored
     *            if null)
     * @return
     */
    public static double[][] randDmm(int n, double[] probs, double[][] means,
            double[] precisions, int[] components) {
        double[][] x = new double[n][];
        // init random number generator

        // multinomial cdf for probs
        double[] cumprobs = (double[]) ArrayUtils.copy(probs);
        int i;
        for (i = 1; i < probs.length; i++) {
            cumprobs[i] += cumprobs[i - 1];
        }
        int len = i;

        for (i = 0; i < n; i++) {
            double randNum = Cokus.randDouble() * cumprobs[len - 1];
            int c = binarySearch(cumprobs, randNum);
            if (components != null) {
                components[i] = c;
            }
            x[i] = randDir(means[c], precisions[c]);
        }
        return x;
    }

    /**
     * beta as two-dimensional Dirichlet
     * 
     * @param aa
     * @param bb
     * @return
     */
    public static double randBeta(double aa, double bb) {

        double[] p = randDir(new double[] { aa, bb });
        return p[0];
    }

    /**
     * randbeta(aa, bb) Generates beta samples, one for each element in aa/bb,
     * and scale 1.
     * 
     * @param aa
     */
    public static double[] randBeta(double[] aa, double[] bb) {
        double[] beta = new double[aa.length];
        for (int i = 0; i < beta.length; i++) {
            beta[i] = randBeta(aa[i], bb[i]);
        }
        return beta;
    }

    /**
     * self-contained gamma generator. Multiply result with scale parameter (or
     * divide by rate parameter). After Teh (npbayes).
     * 
     * @param rr
     *            shape parameter
     * @return
     */
    public static double randGamma(double rr) {
        double aa, bb, cc, dd;
        double uu, vv, ww, xx, yy, zz;

        if (rr <= 0.0) {
            /* Not well defined, set to zero and skip. */
            return 0.0;
        } else if (rr == 1.0) {
            /* Exponential */
            return -Math.log(drand48());
        } else if (rr < 1.0) {
            /* Use Jöhnk's generator */
            cc = 1.0 / rr;
            dd = 1.0 / (1.0 - rr);
            while (true) {
                xx = Math.pow(drand48(), cc);
                yy = xx + Math.pow(drand48(), dd);
                if (yy <= 1.0) {
                    // FIXME: assertion error for rr = 0.010817814317923407
                    // assert yy != 0 && xx / yy > 0 : "rr = " + rr;
                    // INFO: this if is a hack
                    if (yy != 0 && xx / yy > 0) {
                        return -Math.log(drand48()) * xx / yy;
                    }
                }
            }
        } else { /* rr > 1.0 */
            /* Use bests algorithm */
            bb = rr - 1.0;
            cc = 3.0 * rr - 0.75;
            while (true) {
                uu = drand48();
                vv = drand48();
                ww = uu * (1.0 - uu);
                yy = Math.sqrt(cc / ww) * (uu - 0.5);
                xx = bb + yy;
                if (xx >= 0) {
                    zz = 64.0 * ww * ww * ww * vv * vv;
                    assert zz > 0 && bb != 0 && xx / bb > 0;
                    if ((zz <= (1.0 - 2.0 * yy * yy / xx))
                            || (Math.log(zz) <= 2.0 * (bb * Math.log(xx / bb) - yy))) {
                        return xx;
                    }
                }
            }
        }
    }

    /**
     * randgamma(aa) Generates gamma samples, one for each element in aa.
     * 
     * @param aa
     */
    public static double[] randGamma(double[] aa) {
        double[] gamma = new double[aa.length];
        for (int i = 0; i < gamma.length; i++) {
            gamma[i] = randGamma(aa[i]);
        }
        return gamma;
    }

    /**
     * sample from gamma distribution with defined shape a and scale b:
     * <p>
     * x ~ x^(a-1) * exp(-x/b) / ( gamma(a) * b^a )
     * <p>
     * E(x) = ab, V(x) = (ab)^2. Note that instead of the scale parameter b,
     * often a rate parameter r = 1/b is used: E(x) = a/r, V(x) = (a/r)^2. For
     * sampling, the following are equivalent: Gamma(a,1)*b <=> Gamma(a,b), with
     * shape parametrisation; Gamma(a,1)/r <=> Gamma(a,r) with rate
     * parametrisation.
     * 
     * @param shape
     * @param scale
     * @return
     */
    public static double randGamma(double shape, double scale) {
        return randGamma(shape) * scale;
    }

    /**
     * Random permutation of size elements (symbols '0'.. '[size-1]'). This
     * works a bit like sampling without replacement or a factorial.
     * 
     * @param size
     * @return
     */
    public static int[] randPerm(int size) {
        int[] perm = Vectors.range(0, size - 1);
        for (int i = size - 1; i > 0; i--) {
            int k = (int) (Cokus.randDouble() * (i + 1));
            if (k != i) {
                int buf = perm[i];
                perm[i] = perm[k];
                perm[k] = buf;
            }
        }
        return perm;
    }

    /**
     * Random permutation of existing set of integers.
     * 
     * @param set
     * @return
     */
    public final int[] randPerm(int[] set) {
        // works a bit like sampling without replacement or a factorial.
        for (int i = set.length - 1; i > 0; i--) {
            int k = (int) (Cokus.randDouble() * (i + 1));
            if (k != i) {
                int buf = set[i];
                set[i] = set[k];
                set[k] = buf;
            }
        }
        return set;
    }

    /**
     * symmetric Dirichlet sample.
     * 
     * @param aa
     * @return
     */
    public static double[] randDir(double a, int dimension) {
        double[] aa = new double[dimension];
        Arrays.fill(aa, a);
        return randDir(aa);
    }

    /**
     * randdir(aa) generates one Dirichlet sample vector according to the
     * parameters alpha. ORIG: Generates Dirichlet samples, with weights given
     * in aa. The output sums to 1 along normdim, and each such sum corresponds
     * to one Dirichlet sample.
     * 
     * @param aa
     * @param normdim
     * @return
     */
    public static double[] randDir(double[] aa) {
        double[] ww = randGamma(aa);

        double sum = 0;
        for (int i = 0; i < ww.length; i++) {
            sum += ww[i];
        }
        for (int i = 0; i < ww.length; i++) {
            ww[i] /= sum;
        }
        return ww;
    }

    /**
     * randdir(aa) generates one Dirichlet sample vector according to the
     * parameters alpha.
     * 
     * @param mean
     *            (mean_i = alpha_i / sum_j alpha_j)
     * @param precision
     *            (precision = alpha_i / mean_i)
     * @return
     */
    public static double[] randDir(double[] mean, double precision) {
        double[] aa = new double[mean.length];
        for (int i = 0; i < mean.length; i++) {
            aa[i] = mean[i] * precision;
        }
        return randDir(aa);
    }

    /**
     * Generate as many Dirichlet column samples as there are columns (direction
     * = 1; randdir(A, 1)) or row samples as there are rows (direction = 2,
     * randdir(A, 2)) in aa (aa[][]), taking the respective parameters. After
     * Teh (npbayes).
     * 
     * @param aa
     * @param direction
     *            -- 2 is more efficient (row-major Java matrix structure)
     * @return
     */
    public static double[][] randDir(double[][] aa, int direction) {
        double[][] ww = null;
        if (direction == 1) {
            ww = new double[aa.length][aa[0].length];
            double[] dirsmp = new double[aa.length];
            for (int i = 0; i < ww.length; i++) {
                for (int j = 0; j < dirsmp.length; j++) {
                    dirsmp[i] = aa[j][i];
                }
                dirsmp = randDir(aa[i]);
                for (int j = 0; j < dirsmp.length; j++) {
                    ww[j][i] = dirsmp[j];
                }
            }
        } else {
            ww = new double[aa.length][aa[0].length];
            for (int i = 0; i < ww.length; i++) {
                ww[i] = randDir(aa[i]);
            }
        }
        return ww;
    }

    /**
     * Generate n Dirichlet samples taking parameters aa.
     * 
     * @param aa
     * @return
     */
    public static double[][] randDir(double[] aa, int repetitions) {
        double[][] ww = new double[repetitions][aa.length];
        for (int i = 0; i < repetitions; i++) {
            ww[i] = randDir(aa);
        }
        return ww;
    }

    /**
     * Multiply sample a multinomial distribution and return a vector with
     * category frequencies.
     * 
     * @param pp
     * @param repetitions
     * @return vector of frequencies of the categories
     */
    public static int[] randMultFreqs(double[] pp, int repetitions) {
        int[] freqs = new int[pp.length];
        for (int i = 0; i < freqs.length; i++) {
            freqs[i] = 0;
        }

        for (int i = 0; i < repetitions; i++) {
            freqs[randMult(pp)]++;
        }

        return freqs;
    }

    /**
     * Multiply sample a multinomial distribution and return a vector with all
     * samples.
     * 
     * @param pp
     * @param repetitions
     * @return vector of all samples.
     */
    public static int[] randMult(double[] pp, int repetitions) {
        int[] samples = new int[repetitions];

        for (int i = 0; i < repetitions; i++) {
            samples[i] = randMult(pp);
        }
        return samples;

    }

    /**
     * old version of the randMult method
     * 
     * @param pp
     * @return
     */
    public static int randMultSimple(final double[] pp) {

        int i;
        double[] cumPp = new double[pp.length];

        System.arraycopy(pp, 0, cumPp, 0, pp.length);

        for (i = 1; i < pp.length; i++) {
            cumPp[i] += cumPp[i - 1];

        }
        // this automatically normalises.
        double randNum = Cokus.randDouble() * cumPp[i - 1];

        // TODO: use binarySearch().
        for (i = 0; i < cumPp.length; i++) {
            if (cumPp[i] > randNum) {
                break;
            }
        }

        return i;
    }

    public static void testMult() {
        int N = 100000;

        int[][] a = new int[2][N];

        // double[] p = new double[] {.3, .4, .1, .2};
        double[] p = randDir(.1, 10000);
        System.out.println(Vectors.print(p));

        Cokus.seed(4357);
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            a[0][i] = randMult(p);
        }
        long t1 = System.currentTimeMillis();
        System.out.println();
        Cokus.seed(4357);

        long t2 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            a[1][i] = randMult(p);
        }
        long t3 = System.currentTimeMillis();

        // for (int i = 0; i < a[0].length; i++) {
        // System.out.println(a[0][i] + " " + a[1][i]);
        // if (a[0][i] != a[1][i]) {
        // System.err.println("a0 != a1");
        // }
        // }
        System.out.println("linear search: " + (t1 - t0) + " binary search: "
                + (t3 - t2));
    }

    /**
     * Creates one multinomial sample given the parameter vector pp. Each
     * category is named after the index (0-based!) of the respective element of
     * pp; Sometimes called categorical distribution (e.g., in BUGS). This
     * version uses a binary search algorithm and does not require
     * normalisation.
     */
    public static int randMult(final double[] pp) {

        int i;
        double[] cumPp = new double[pp.length];

        System.arraycopy(pp, 0, cumPp, 0, pp.length);

        for (i = 1; i < pp.length; i++) {
            cumPp[i] += cumPp[i - 1];

        }
        // this automatically normalises.
        double randNum = Cokus.randDouble() * cumPp[i - 1];

        // TODO: use insertion point formula in Array.binarySearch()
        i = binarySearch(cumPp, randNum);

        // System.out.println(Vectors.print(pp) + " " + i);

        return i;
    }

    /**
     * Creates one multinomial sample given the parameter vector pp. Each
     * category is named after the index (0-based!) of the respective element of
     * pp; Sometimes called categorical distribution (e.g., in BUGS). This
     * version uses a binary search algorithm and does not require
     * normalisation. Note that the parameters used <i>directly</i> changed
     * because the multinomial is cumulated to save memory and copying time.
     */
    public static int randMultDirect(double[] pp) {

        int i;
        for (i = 1; i < pp.length; i++) {
            pp[i] += pp[i - 1];

        }
        // this automatically normalises.
        double randNum = Cokus.randDouble() * pp[i - 1];
        lastRand = randNum;

        // TODO: use insertion point formula in Array.binarySearch()
        i = binarySearch(pp, randNum);

        // System.out.println(Vectors.print(pp) + " " + i);

        return i;
    }

    /**
     * Like randMultDirect, but the random number is given as argument.
     */
    public static int randMultDirect(double[] pp, double rand) {

        int i;
        for (i = 1; i < pp.length; i++) {
            pp[i] += pp[i - 1];

        }
        // this automatically normalises.

        // TODO: use insertion point formula in Array.binarySearch()
        i = binarySearch(pp, rand);

        // System.out.println(Vectors.print(pp) + " " + i);

        return i;
    }

    /**
     * perform a binary search and return the first index i at which a[i] >= p.
     * Adapted from java.util.Arrays.binarySearch.
     * 
     * @param a
     * @param p
     * @return
     */
    public static int binarySearch(double[] a, double p) {
        if (p < a[0]) {
            return 0;
        }
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            double midVal = a[mid];

            if (midVal < p) {
                low = mid + 1;
            } else if (midVal > p) {
                if (a[mid - 1] < p)
                    return mid;
                high = mid - 1;
            } else {
                return mid;
            }
        }
        // out of range.
        return a.length;
    }

    /**
     * draw a binomial sample (by counting Bernoulli samples).
     * 
     * @param N
     * @param p
     */
    public static int randBinom(double N, double p) {
        int n = 0;
        for (int i = 0; i < N; i++) {
            if (randBernoulli(p) == 1) {
                n++;
            }
        }
        return n;
    }

    /**
     * draw a Bernoulli sample.
     * 
     * @param p
     *            success probability
     * @return 1 if sucessful, 0 otherwise
     */
    public static int randBernoulli(double p) {
        double a = Cokus.randDouble();
        if (a < p) {
            return 1;
        }
        return 0;
    }

    /**
     * randconparam(alpha,numdata,numclass,aa,bb,numiter) Generates a sample
     * from a concentration parameter of a HDP with gamma(aa,bb) prior, and
     * number of classes and data items given in numdata, numclass (has to be
     * row vectors). Uses auxiliary variable method, for numiter iterations.
     * <p>
     * Modification of Escobar and West. Works for multiple groups of data.
     * numdata, numclass are row vectors, one element per group. After Teh
     * (npbayes).
     * 
     * @param alpha
     *            alpha
     * @param numgroup
     *            number of components ??
     * @param numdata
     *            number of data items per class
     * @param numtable
     *            number of per DP
     * @param alphaa
     *            hyperparameter (gamma shape)
     * @param alphab
     *            hyperparameter (gamma scale)
     * @param numiter
     *            number of iterations
     * @return
     */
    public static double randConParam(double alpha, int numgroup,
            int[] numdata, int[] numtable, double alphaa, double alphab,
            int numiter) {
        int iter, jj, nd, zz;
        double aa, bb, eta;

        // Teh: Escobar and West's method for single Gamma.

        for (iter = 0; iter < numiter; iter++) {
            aa = alphaa;
            bb = alphab;
            for (jj = 0; jj < numgroup; jj++) {
                nd = numdata[jj];
                eta = randBeta(alpha + 1.0, nd);
                // zz = (drand48() * (alpha + nd) < nd);
                zz = (drand48() * (alpha + nd) < nd) ? 1 : 0;

                aa += numtable[jj] - (zz);
                bb -= Math.log(eta);
            }
            alpha = randGamma(aa) / bb;
        }
        return alpha;
    }

    /**
     * Sample the Dirichlet process concetration parameter given the topic and
     * data counts and gamma hyperparameters alphaa and alphab. After Escobar
     * and West (1995).
     * 
     * @param alpha
     * @param numdata
     * @param numtopic
     * @param alphaa
     * @param alphab
     * @param numiter
     * @return
     */
    public static double randConParam(double alpha, int numdata, int numtopic,
            double alphaa, double alphab, int numiter) {
        int iter, jj, nd, zz;
        double aa, bb, eta;

        // Escobar and West's method
        double alphaAvg = 0.;

        for (iter = 0; iter < numiter; iter++) {
            aa = alphaa;
            bb = alphab;

            // e+w (14)
            eta = randBeta(alpha + 1.0, numdata);

            // e+w (13)
            double pi = 1 / (numdata * (alphab - Math.log(eta))
                    / (alphaa + numtopic - 1) + 1);
            // choose between the two gamma components
            zz = (drand48() > pi) ? 1 : 0;

            // sample gamma
            aa += numtopic - zz;
            bb -= Math.log(eta);
            // * or / ? (scale or rate?)
            alpha = randGamma(aa) / bb;
            alphaAvg += alpha;
        }
        return alphaAvg / numiter;
    }

    public static CrpData randCrp(double alpha, int numdata) {
        return randCrp(new double[] { alpha }, numdata);
    }

    /**
     * [cc numclass] = randcrp(alpha,numdata) Generates a partition of numdata
     * items with concentration parameter alpha, which can be an array, in which
     * case the Chinese restaurant process has "two new tables to chose for each
     * new customer". cc is sequence of indicator variables denoting which class
     * each data item is in ("on which table each customer sits"), and numclass
     * is the generated number of classes. After Teh (npbayes).
     * 
     * @param alpha
     * @param numdata
     * @return
     */
    public static CrpData randCrp(double[] alpha, int numdata) {
        // function [cc, numclass] = randcrp(alpha, numdata);
        // % generates a CRP partition of numdata items, with concentration
        // parameter
        // % alpha.
        //
        // cc = zeros(1,numdata);
        // weights = alpha;
        // ? vector

        double[] weights = Vectors.copy(alpha);
        // numclass = 0;
        CrpData crp = new CrpData(numdata);
        //
        // for ii = 1:numdata
        // cc(ii) = randmult(weights);
        // if cc(ii) > numclass
        // weights = [weights(1:numclass) 1 alpha];
        // numclass = cc(ii);
        // else
        // weights(cc(ii)) = weights(cc(ii)) + 1;
        // end
        // end

        for (int ii = 0; ii < numdata; ii++) {
            crp.cc[ii] = randMult(weights);
            if (crp.cc[ii] > crp.numclass) {
                // has the multinomial weights (Vector better)
                // add one component weight (1)
                weights = Vectors.concat(Vectors.subVector(weights, 0,
                    crp.numclass - 1), new double[] { 1 }, alpha);
                crp.numclass = crp.cc[ii];
            } else {
                weights[crp.cc[ii]]++;
            }
        }
        return crp;
    }

    /**
     * data structure for a Chinese restaurant process CrpData
     * 
     * @author heinrich
     */
    public static class CrpData {
        public int[] cc;

        public int numclass;

        public CrpData(int numdata) {
            cc = Vectors.ones(numdata, 0);
            numclass = 0;
        }
    }

    /**
     * randnumtable(weights,maxtable) For each entry in weights and maxtables,
     * generates the number of tables given concentration parameter (weights)
     * and number of data items (maxtable). From npbayes-2.1. enumClass seems to
     * be the expected value of randNumTable. After Teh (npbayes).
     * 
     * @param weights
     * @param maxtable
     * @return
     */
    public static int randNumTable(double alpha, int numdata) {
        int ii, numtable;

        if (numdata == 0) {
            return 0;
        } else {
            numtable = 1;
            for (ii = 1; ii < numdata; ii++) {
                if (drand48() < alpha / (ii + alpha))
                    numtable++;
            }
            return numtable;
        }
    }

    /**
     * randstick(alpha,numclass) Generates stick-breaking weights with
     * concentration parameter for numclass "sticks". XXX: untested. After Teh
     * (npbayes).
     * 
     * @param alpha
     * @param numclass
     * @return
     */
    public static double[] randStick(double[] alpha, int numclass) {
        // function beta = randstick(alpha,numclass);
        //
        // one = ones(1,numclass);
        // zz = randbeta(one, alpha*one);
        // beta = zz .* cumprod([1 1-zz(1:numclass-1)]);

        double[] beta = new double[numclass];
        double[] one = Vectors.ones(numclass, 1.0);
        double[] zz = new double[numclass];
        for (int i = 0; i < zz.length; i++) {
            zz[i] = randBeta(1, alpha[i]);
        }
        beta[0] = 1;
        for (int i = 1; i < numclass; i++) {
            beta[i] = (1 - zz[i - 1]) * beta[i - 1];
        }
        for (int i = 0; i < numclass; i++) {
            beta[i] *= zz[i];
        }
        return beta;
    }

    /**
     * enumclass(alpha,numdata) The expected number of tables in a CRP with
     * concentration parameter alpha and numdata items. After Teh (npbayes).
     * 
     * @param alpha
     * @param numdata
     * @return
     */
    public static double enumClass(double alpha, int numdata) {
        // function numclass = enumclass(alpha,numdata);
        //
        // numclass = alpha*sum(1./(alpha-1+(1:numdata)));
        double numclass = 0;
        for (int ii = 1; ii <= numdata; ii++) {
            numclass += 1 / ((alpha - 1) + ii);
        }
        return numclass * alpha;
    }

    /**
     * create a random string of length alphanumeric characters.
     * 
     * @param length
     *            of output
     * @param alphabet
     *            alphabet to be used or null
     * @return
     */
    public static String randString(int length, byte[] alphabet) {
        if (alphabet == null)
            alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
                    .getBytes();
        byte[] pass = new byte[length];

        for (int k = 0; k < length; k++) {
            int i = (int) Math.floor(Cokus.randDouble() * alphabet.length);
            pass[k] = alphabet[i];
        }
        return new String(pass);
    }

    /**
     * meanlik(lik) Computes estimated likelihood from individual samples.
     * Basically does a harmonic mean of lik in 3rd dimension, followed by
     * normal mean in 2nd. After Teh (npbayes).
     * 
     * @param lik
     * @return
     */
    public static double meanLik(double lik) {
        // function lik = meanlik(lik);
        //
        // lik = logmeanexp(-logmeanexp(-lik,3),2);

        return 0;
    }

    /**
     * TODO: MAXSTIRLING should be made variable
     */
    protected static int MAXSTIRLING = 40;

    /**
     * maximum stirling number in allss
     */
    protected static int maxnn = 1;

    /**
     * contains all stirling number iteratively calculated so far
     */
    protected static double[][] allss = new double[MAXSTIRLING][];

    /**
     *
     */
    protected static double[] logmaxss = new double[MAXSTIRLING];

    protected static double lmss = 0;

    /**
     * [ss lmss] = stirling(nn) Gives unsigned Stirling numbers of the first
     * kind s(nn,*) in ss. ss(i) = s(nn,i-1). ss is normalized so that maximum
     * value is 1, and the log of normalization is given in lmss (static
     * variable). After Teh (npbayes).
     * 
     * @param nn
     * @return
     */
    public static double[] stirling(int nn) {
        if (allss[0] == null) {
            allss[0] = new double[1];
            allss[0][0] = 1;
            logmaxss[0] = 0;
        }

        if (nn > maxnn) {
            for (int mm = maxnn; mm < nn; mm++) {
                int len = allss[mm - 1].length + 1;
                allss[mm] = new double[len];
                for (int xx = 0; xx < len; xx++) {
                    // allss{mm} = [allss{mm-1}*(mm-1) 0] + ...
                    allss[mm][xx] += (xx < len - 1) ? allss[mm - 1][xx] * mm
                            : 0;
                    // [0 allss{mm-1}];
                    allss[mm][xx] += (xx == 0) ? 0 : allss[mm - 1][xx - 1];
                }
                double mss = Vectors.max(allss[mm]);
                Vectors.mult(allss[mm], 1 / mss);
                logmaxss[mm] = logmaxss[mm - 1] + Math.log(mss);
            }
            maxnn = nn;
        }
        lmss = logmaxss[nn - 1];
        return allss[nn - 1];
    }

    /**
     * sample number of components m that a DP(alpha, G0) has after n samples.
     * This was first published by Antoniak (1974). TODO: another check, as
     * direct simulation of CRP tables produces higher results
     * 
     * @param alpha
     * @param n
     * @return
     */
    public static int randAntoniak(double alpha, int n) {
        double[] p = stirling(n);
        double aa = 1;
        for (int m = 0; m < p.length; m++) {
            p[m] *= aa;
            aa *= alpha;
        }
        // alternatively using direct simulation of CRP
        // int R = 20;
        // double ainv = 1 / (alpha);
        // double nt = 0;
        // double[] p = new double[n];
        // for (int r = 0; r < R; r++) {
        // for (int m = 0; m < n; m++) {
        // for (int t = 0; t < n; t++, nt += ainv) {
        // p[m] += randBernoulli(1 / (nt + 1));
        // }
        // }
        // }
        // Vectors.mult(p, 1. / R);
        System.out.println(Vectors.print(p));
        return randMultDirect(p) + 1;
    }

    /**
     * @param numclass
     * @return
     */
    public static double randUniform(double numvalue) {
        return drand48() * numvalue;
    }

    /**
     * @param numclass
     * @return
     */
    public static int randUniform(int numvalue) {
        return (int) Math.floor(drand48() * numvalue);
    }

}
